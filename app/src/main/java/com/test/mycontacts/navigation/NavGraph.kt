package com.test.mycontacts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.mycontacts.ui.ContactsScreen
import com.test.mycontacts.contacts.ContactsViewModel
import com.test.mycontacts.ui.CreateContactScreen
import com.test.mycontacts.create_contact.CreateContactViewModel

@Composable
fun ContactsNavGraph(navHostController: NavHostController) {

  NavHost(navHostController, startDestination = Screens.ContactsScreen.route) {
    composable(route = Screens.ContactsScreen.route) {
      val viewModel = hiltViewModel<ContactsViewModel>()
      val contacts by viewModel.contacts.collectAsState()
      ContactsScreen(navHostController = navHostController, contacts = contacts)
    }
    composable(route = Screens.CreateContactScreen.route) {
      val viewModel = hiltViewModel<CreateContactViewModel>()
      CreateContactScreen(
        navHostController = navHostController,
        state = viewModel.state,
        onEvent = viewModel::onEvent,
      )
    }
  }
}