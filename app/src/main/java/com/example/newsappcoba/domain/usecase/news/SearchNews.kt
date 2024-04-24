package com.example.newsappcoba.domain.usecase.news

import androidx.paging.PagingData
import com.example.newsappcoba.domain.model.Article
import com.example.newsappcoba.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(
            searchQuery = searchQuery,
            sources = sources
        )
    }
}