package com.test.domain.usecase

import com.test.domain.repository.ContactsRepo
import com.test.mylibrary.local.ContactsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetContactsUseCaseImpl @Inject constructor(private val contactsRepo: ContactsRepo):
  GetContactsUseCase {

  override fun invoke(): Flow<List<ContactsEntity>> = contactsRepo.getContacts()
}