package com.test.domain.repository

import com.test.mylibrary.local.ContactsEntity
import kotlinx.coroutines.flow.Flow

interface ContactsRepo {

  suspend fun insertContact(contactsEntity: ContactsEntity)

  fun getContacts(): Flow<List<ContactsEntity>>
}