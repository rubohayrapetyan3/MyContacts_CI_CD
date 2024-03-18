package com.test.mycontacts.di

import com.test.common.Constants
import com.test.mylibrary.local.ContactsEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

@Module
@InstallIn(SingletonComponent::class)
object RealmModule {

  @Provides
  fun provideDatabase(): Realm {
    val config = RealmConfiguration.Builder(
      schema = setOf(
        ContactsEntity::class
      )
    )
      .name(Constants.DB_CONTACTS)
      .schemaVersion(1)
      .build()
    return Realm.open(config)
  }
}