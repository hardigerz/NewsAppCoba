package com.example.newsappcoba.di

import android.app.Application
import com.example.newsappcoba.data.manager.LocalUserManagerImpl
import com.example.newsappcoba.domain.manager.LocalUserManager
import com.example.newsappcoba.domain.usecase.AppEntryUseCase
import com.example.newsappcoba.domain.usecase.ReadAppEntry
import com.example.newsappcoba.domain.usecase.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager =LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCase(
        localUserManager: LocalUserManager
    ) =AppEntryUseCase(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}