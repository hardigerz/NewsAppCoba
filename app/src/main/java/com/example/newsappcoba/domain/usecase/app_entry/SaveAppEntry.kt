package com.example.newsappcoba.domain.usecase.app_entry

import com.example.newsappcoba.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
)
{
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}