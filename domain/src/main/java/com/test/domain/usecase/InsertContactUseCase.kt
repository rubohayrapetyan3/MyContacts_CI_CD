package com.test.domain.usecase

import com.test.mylibrary.local.ContactsEntity

interface InsertContactUseCase {
  suspend operator fun invoke(contactsEntity: ContactsEntity)
}