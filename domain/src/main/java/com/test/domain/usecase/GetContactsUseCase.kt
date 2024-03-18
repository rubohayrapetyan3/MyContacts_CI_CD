package com.test.domain.usecase

import com.test.mylibrary.local.ContactsEntity
import kotlinx.coroutines.flow.Flow

interface GetContactsUseCase {
  operator fun invoke(): Flow<List<ContactsEntity>>
}