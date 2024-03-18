package com.test.mycontacts.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.test.mycontacts.R
import com.test.mycontacts.create_contact.CreateContactContract

@Composable
fun CreateContactScreen(
  navHostController: NavHostController,
  state: CreateContactContract.CreateContactState,
  onEvent: (CreateContactContract.CreateContactEvent) -> Unit,
) {
  Column(modifier = Modifier.fillMaxSize()) {
    val nameFocusRequester = FocusRequester()
    val surnameFocusRequester = FocusRequester()
    val phoneFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    TopBar(
      navHostController = navHostController,
      onSaveClick = {
        onEvent(CreateContactContract.CreateContactEvent.Save)
      }
    )

    Spacer(modifier = Modifier.height(20.dp))

    val pickImageLauncher =
      rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        onEvent(CreateContactContract.CreateContactEvent.PictureUriChangedEvent(pictureUri = uri))
      }

    UserPicture(
      imageUri = state.pictureUri,
      onUploadPicClick = {
        pickImageLauncher.launch("image/*")
      })

    InputField(
      hint = stringResource(id = R.string.hint_first_name),
      focusRequester = nameFocusRequester,
      imeAction = ImeAction.Next,
      isError = state.name.isEmpty() && state.isSaveClicked,
      errorMessage = stringResource(id = R.string.error_message_name),
      value = state.name,
      onValueChanged = {
        onEvent(
          CreateContactContract.CreateContactEvent.NameChangedEvent(
            name = it
          )
        )
      },
      onNext = {
        surnameFocusRequester.requestFocus()
      }
    )

    InputField(
      hint = stringResource(id = R.string.hint_last_name),
      focusRequester = surnameFocusRequester,
      imeAction = ImeAction.Next,
      value = state.surname ?: "",
      onValueChanged = {
        onEvent(
          CreateContactContract.CreateContactEvent.SurnameChangedEvent(
            surname = it
          )
        )
      },
      onNext = {
        phoneFocusRequester.requestFocus()
      }
    )

    InputField(
      hint = stringResource(id = R.string.hint_phone),
      focusRequester = phoneFocusRequester,
      imeAction = ImeAction.Done,
      keyboardType = KeyboardType.Phone,
      isError = state.phone.isEmpty() && state.isSaveClicked,
      errorMessage = stringResource(id = R.string.error_message_phone),
      value = state.phone,
      onValueChanged = {
        onEvent(
          CreateContactContract.CreateContactEvent.PhoneChangedEvent(
            phone = it
          )
        )
      },
      onDone = {
        focusManager.clearFocus()
      }
    )

    if (state.isContactSaved) {
      navHostController.navigateUp()
    }
  }
}

@Composable
private fun TopBar(navHostController: NavHostController, onSaveClick: () -> Unit) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      modifier = Modifier.clickable {
        navHostController.navigateUp()
      },
      imageVector = Icons.Default.Close, contentDescription = "Close"
    )

    Text(
      modifier = Modifier,
      fontSize = 18.sp,
      fontWeight = FontWeight(600),
      text = stringResource(id = R.string.title_create_contact)
    )

    Spacer(modifier = Modifier.width(60.dp))

    Button(
      modifier = Modifier
        .padding(16.dp)
        .clip(CircleShape),
      colors = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.blue)
      ),
      onClick = {
        onSaveClick()
      }) {
      Text(
        color = colorResource(id = R.color.white),
        text = stringResource(id = R.string.button_save)
      )
    }
  }
}

@Composable
private fun UserPicture(imageUri: Uri? = null, onUploadPicClick: () -> Unit) {

  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Box(
      modifier = Modifier
        .sizeIn(minHeight = 80.dp, minWidth = 80.dp)
        .clip(CircleShape)
        .background(color = colorResource(id = R.color.light_blue))
        .clickable {
          onUploadPicClick()
        },
      contentAlignment = Alignment.Center
    ) {
      if (imageUri == null) {
        Icon(imageVector = Icons.Default.Face, contentDescription = "Face")
      } else {
        AsyncImage(
          model = imageUri,
          contentDescription = "User img",
          modifier = Modifier
            .size(80.dp)
            .clip(CircleShape),
          contentScale = ContentScale.FillBounds,
        )
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    Text(
      modifier = Modifier.clickable {
        onUploadPicClick()
      },
      color = colorResource(id = R.color.light_blue),
      text = stringResource(id = R.string.text_add_picture)
    )
  }
}

@Composable
private fun InputField(
  hint: String,
  focusRequester: FocusRequester,
  imeAction: ImeAction,
  modifier: Modifier = Modifier,
  keyboardType: KeyboardType = KeyboardType.Text,
  value: String,
  onValueChanged: (String) -> Unit,
  onNext: (() -> Unit)? = null,
  onDone: (() -> Unit)? = null,
  isError: Boolean = false,
  errorMessage: String? = null
) {

  OutlinedTextField(
    value = value,
    onValueChange = { newText ->
      onValueChanged(newText)
    },
    label = { Text(hint) },
    modifier = modifier
      .padding(16.dp)
      .focusRequester(focusRequester)
      .fillMaxWidth(),
    keyboardOptions = KeyboardOptions.Default.copy(
      keyboardType = keyboardType,
      imeAction = imeAction
    ),
    keyboardActions = KeyboardActions(
      onNext = {
        onNext?.invoke()
      },
      onDone = {
        onDone?.invoke()
      }
    ),
    isError = isError,
    singleLine = true
  )

  if (isError && !errorMessage.isNullOrBlank()) {
    Text(
      text = errorMessage,
      color = Color.Red,
      fontSize = 12.sp,
      modifier = Modifier.padding(start = 16.dp, top = 4.dp)
    )
  }
}

@Preview
@Composable
fun CreateContactScreenPreview() {
  CreateContactScreen(
    navHostController = rememberNavController(),
    state = CreateContactContract.CreateContactState(
      name = "Vazgen"
    ),
    onEvent = {}
  )
}