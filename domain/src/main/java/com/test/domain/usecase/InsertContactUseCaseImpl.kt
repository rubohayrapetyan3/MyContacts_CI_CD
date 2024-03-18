package com.test.domain.usecase

import com.test.domain.repository.ContactsRepo
import com.test.mylibrary.local.ContactsEntity
import javax.inject.Inject

class InsertContactUseCaseImpl @Inject constructor(private val contactsRepo: ContactsRepo) :
  InsertContactUseCase {

  override suspend fun invoke(contactsEntity: ContactsEntity) {
    contactsRepo.insertContact(contactsEntity = contactsEntity)
  }
}