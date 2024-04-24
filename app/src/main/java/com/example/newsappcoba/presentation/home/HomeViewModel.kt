package com.example.newsappcoba.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsappcoba.domain.usecase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {
    val news =newsUseCases.getNews(
        sources = listOf("bbc-news","business-insider-uk","al-jazeera-english","bbc-sport")
    ).cachedIn(viewModelScope)
}