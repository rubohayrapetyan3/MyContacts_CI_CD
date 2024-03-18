package com.test.domain

import com.test.domain.repository.ContactsRepo
import com.test.domain.usecase.GetContactsUseCaseImpl
import com.test.mylibrary.local.ContactsEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetContactContactUseCaseImplTest {

  private lateinit var contactsRepo: ContactsRepo
  private lateinit var getContactUseCaseImpl: GetContactsUseCaseImpl

  @Before
  fun setUp() {
    contactsRepo = mockk()
    getContactUseCaseImpl = GetContactsUseCaseImpl(contactsRepo)
  }

  @Test
  fun getDataSuccess() = runBlocking {
    // Given
    val mockContactsList = listOf(
      ContactsEntity("Armen", "armen@gmail.com", "+3741010222"),
      ContactsEntity("Armen2", "armen2@gmail.com", "+3741010224"),
    )

    // Expectation
    coEvery { contactsRepo.getContacts() } returns flowOf(mockContactsList)

    // When
    val result: List<ContactsEntity> = getContactUseCaseImpl().last()

    // Then
    assertEquals(mockContactsList, result)
  }
}