package com.test.mycontacts.create_contact

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.common.parseToString
import com.test.domain.usecase.InsertContactUseCase
import com.test.mylibrary.local.ContactsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateContactViewModel @Inject constructor(
  private val insertContactUseCase: InsertContactUseCase
) : ViewModel() {

  var state by mutableStateOf(CreateContactContract.CreateContactState())

  fun onEvent(event: CreateContactContract.CreateContactEvent) {
    when (event) {
      is CreateContactContract.CreateContactEvent.NameChangedEvent -> {
        state = state.copy(isSaveClicked = false)
        state = state.copy(name = event.name)
      }

      is CreateContactContract.CreateContactEvent.SurnameChangedEvent -> {
        state = state.copy(surname = event.surname)
      }

      is CreateContactContract.CreateContactEvent.PhoneChangedEvent -> {
        state = state.copy(isSaveClicked = false)
        state = state.copy(phone = event.phone)
      }

      is CreateContactContract.CreateContactEvent.PictureUriChangedEvent -> {
        state = state.copy(pictureUri = event.pictureUri)
      }

      is CreateContactContract.CreateContactEvent.Save -> {
        state = state.copy(isSaveClicked = true)
        checkFields()
      }
    }
  }

  private fun addContactToDb(contactsEntity: ContactsEntity) {
    viewModelScope.launch {
      insertContactUseCase(contactsEntity = contactsEntity)
    }
  }

  private fun checkFields() = with(state) {
    if (name.isEmpty() || phone.isEmpty()) {
      return@with
    } else {
      addContactToDb(
        ContactsEntity(
          name = state.name,
          surname = state.surname,
          phone = state.phone,
          pictureUri = state.pictureUri?.parseToString()
        )
      )
      state = state.copy(isContactSaved = true)
    }
  }
}