package com.test.domain

import com.test.domain.repository.ContactsRepo
import com.test.domain.usecase.InsertContactUseCaseImpl
import com.test.mylibrary.local.ContactsEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class InsertContactsUseCaseImplTest {
  private lateinit var contactsRepo: ContactsRepo
  private lateinit var insertContactUseCaseImpl: InsertContactUseCaseImpl

  @Before
  fun setUp() {
    contactsRepo = mockk()
    insertContactUseCaseImpl = InsertContactUseCaseImpl(contactsRepo)
  }

  @Test
  fun `insertContact with correct parameters`() = runBlocking {
    // Given
    val contactsEntity = ContactsEntity("Arman", "Hasratyan", "+123456789")

    // Expectation
    coEvery {
      contactsRepo.insertContact(contactsEntity = any())
    } returns Unit

    // When
    insertContactUseCaseImpl(contactsEntity)

//     Then
    coVerify {
      contactsRepo.insertContact(contactsEntity = any())
    }
  }
}