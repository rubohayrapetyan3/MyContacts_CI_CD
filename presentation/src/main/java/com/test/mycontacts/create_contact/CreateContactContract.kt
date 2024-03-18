package com.test.mycontacts.create_contact

import android.net.Uri

class CreateContactContract {

  sealed class CreateContactEvent {
    data class NameChangedEvent(val name: String) : CreateContactEvent()
    data class SurnameChangedEvent(val surname: String?) : CreateContactEvent()
    data class PhoneChangedEvent(val phone: String) : CreateContactEvent()
    data class PictureUriChangedEvent(val pictureUri: Uri?) : CreateContactEvent()

    data object Save : CreateContactEvent()
  }

  data class CreateContactState(
    val name: String = "",
    val surname: String? = null,
    val phone: String = "",
    val pictureUri: Uri? = null,
    val isSaveClicked: Boolean = false,
    val isContactSaved: Boolean = false
  )
}