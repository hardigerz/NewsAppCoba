package com.example.newsappcoba.domain.usecase.news

import androidx.paging.PagingData
import com.example.newsappcoba.domain.model.Article
import com.example.newsappcoba.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}