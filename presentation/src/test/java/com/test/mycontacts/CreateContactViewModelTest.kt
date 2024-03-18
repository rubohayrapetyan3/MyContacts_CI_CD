package com.test.mycontacts

import android.net.Uri
import com.test.common.parseToUri
import com.test.mycontacts.create_contact.CreateContactContract
import com.test.mycontacts.create_contact.CreateContactViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CreateContactViewModelTest {

  private lateinit var viewModel: CreateContactViewModel

  @ExperimentalCoroutinesApi
  @Before
  fun setupDispatcher() {
    viewModel = CreateContactViewModel(mockk(relaxed = true))
  }

  @Test
  fun `onEvent NameChangedEvent should update name in state`() {
    val given = "Armen"
    viewModel.onEvent(CreateContactContract.CreateContactEvent.NameChangedEvent(given))
    val expect = viewModel.state.name
    assertEquals(given, expect)
  }

  @Test
  fun `onEvent SurnameChangedEvent should update surname in state`() {
    val given = "Karapetyan"
    viewModel.onEvent(CreateContactContract.CreateContactEvent.SurnameChangedEvent(given))
    val expect = viewModel.state.surname
    assertEquals(given, expect)
  }

  @Test
  fun `onEvent PhoneChangedEvent should update phone in state`() {
    val given = "+85 45 4456"
    viewModel.onEvent(CreateContactContract.CreateContactEvent.PhoneChangedEvent(phone = given))
    val expect = viewModel.state.phone
    assertEquals(given, expect)
  }

  @Test
  fun `onEvent PictureUriChangedEvent should update PictureUri in state`() {
    mockkStatic(Uri::class)
    every { Uri.parse(any()) } returns mockk()

    val given = "image/*/something".parseToUri()
    viewModel.onEvent(CreateContactContract.CreateContactEvent.PictureUriChangedEvent(
      pictureUri = given
    ))
    val expect = viewModel.state.pictureUri
    assertEquals(given, expect)
  }
}