package com.test.mycontacts.navigation

import com.test.common.Constants

sealed class Screens(val route: String) {
  data object ContactsScreen: Screens(route = Constants.ROUTE_CONTACTS)
  data object CreateContactScreen: Screens(route = Constants.ROUTE_CREATE_CONTACT)
}