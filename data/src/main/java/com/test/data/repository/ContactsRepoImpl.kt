package com.test.data.repository

import com.test.domain.repository.ContactsRepo
import com.test.mylibrary.local.ContactsEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContactsRepoImpl @Inject constructor(private val realm: Realm) : ContactsRepo {
  override suspend fun insertContact(contactsEntity: ContactsEntity) {
    realm.write {
      val contact = ContactsEntity().apply {
        this.name = contactsEntity.name
        this.surname = contactsEntity.surname
        this.phone = contactsEntity.phone
        this.pictureUri = contactsEntity.pictureUri
      }

      copyToRealm(instance = contact, updatePolicy = UpdatePolicy.ALL)
    }
  }

  override fun getContacts(): Flow<List<ContactsEntity>> = realm
      .query<ContactsEntity>()
      .asFlow()
      .map {
        it.list.toList()
      }
}