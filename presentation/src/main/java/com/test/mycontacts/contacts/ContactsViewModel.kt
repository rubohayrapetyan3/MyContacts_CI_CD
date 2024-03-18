package com.test.mycontacts.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.usecase.GetContactsUseCase
import com.test.mylibrary.local.ContactsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
  getContactsUseCase: GetContactsUseCase
): ViewModel() {

  val contacts: StateFlow<List<ContactsEntity>> = getContactsUseCase()
    .stateIn(
      viewModelScope,
      SharingStarted.WhileSubscribed(),
      emptyList()
    )
}