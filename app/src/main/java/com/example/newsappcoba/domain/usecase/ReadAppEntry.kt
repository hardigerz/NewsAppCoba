package com.example.newsappcoba.domain.usecase

import com.example.newsappcoba.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (
    private val localUserManager: LocalUserManager
)
{
    operator fun invoke() : Flow<Boolean>{
      return  localUserManager.readAppEntry()
    }
}