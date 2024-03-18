package com.test.mycontacts.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.test.common.isTrue
import com.test.common.parseToUri
import com.test.mycontacts.R
import com.test.mycontacts.navigation.Screens
import com.test.mylibrary.local.ContactsEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ContactsScreen(navHostController: NavHostController, contacts: List<ContactsEntity>) {

  Box(modifier = Modifier.fillMaxSize()) {
    UsersContent(contacts = contacts.toImmutableList(), navHostController = navHostController)
  }
}

@Composable
private fun UsersContent(
  contacts: ImmutableList<ContactsEntity>,
  navHostController: NavHostController
) {
  LazyColumn(modifier = Modifier.fillMaxSize()) {

    item {
      CreateNewContact {
        navHostController.navigate(Screens.CreateContactScreen.route)
      }

      Spacer(modifier = Modifier.height(10.dp))
    }

    items(contacts, key = { contacts -> contacts._id.toHexString() }) { contact ->
      ContactsItem(contact = contact)
    }
  }
}

@Composable
private fun CreateNewContact(onCreateNewContactClick: () -> Unit) {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Row(
      modifier = Modifier.clickable {
        onCreateNewContactClick()
      },
      verticalAlignment = Alignment.CenterVertically
    ) {

      Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add")

      Spacer(modifier = Modifier.width(10.dp))

      Text(text = stringResource(id = R.string.text_create_new_contact))
    }
  }
}

@Composable
private fun ContactsItem(contact: ContactsEntity?) {
  contact?.let {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .sizeIn(minHeight = 60.dp, minWidth = 60.dp)
          .clip(CircleShape)
          .background(color = colorResource(id = R.color.light_blue)),
        contentAlignment = Alignment.Center
      ) {
        if (contact.pictureUri == null) {
          Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = "contacts image"
          )
        } else {
          AsyncImage(
            model = contact.pictureUri?.parseToUri(),
            contentDescription = "User img",
            modifier = Modifier
              .size(60.dp)
              .clip(CircleShape),
            contentScale = ContentScale.FillBounds,
          )
        }
      }

      Spacer(modifier = Modifier.width(10.dp))

      Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = it.name)
        it.surname?.isNotEmpty()?.isTrue {
          Text(text = it.surname ?: "")
        }
        Text(text = it.phone)
      }
    }
    Spacer(modifier = Modifier.height(10.dp))
  }
}

@Preview
@Composable
fun ContactsScreenPreview() {
  val contacts = listOf(
    ContactsEntity().apply {
      this.name = "Karen"
      this.surname = "sadv"
      this.phone = "077405020"
    }
  )
  ContactsScreen(navHostController = rememberNavController(), contacts)
}