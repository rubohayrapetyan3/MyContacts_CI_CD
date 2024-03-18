package com.test.mycontacts.di

import com.test.data.repository.ContactsRepoImpl
import com.test.domain.repository.ContactsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class RepositoryModule {
  @Binds
  abstract fun bindContactsRepository(repository: ContactsRepoImpl): ContactsRepo
}