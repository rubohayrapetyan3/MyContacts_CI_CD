package com.test.mycontacts.di

import com.test.domain.usecase.GetContactsUseCase
import com.test.domain.usecase.GetContactsUseCaseImpl
import com.test.domain.usecase.InsertContactUseCase
import com.test.domain.usecase.InsertContactUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UseCaseModule {
  @Binds
  abstract fun bindGetContactsUseCase(useCase: GetContactsUseCaseImpl): GetContactsUseCase

  @Binds
  abstract fun bindInsertContactUseCase(useCase: InsertContactUseCaseImpl): InsertContactUseCase
}