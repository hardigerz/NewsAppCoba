package com.example.newsappcoba.remote.dto

import com.example.newsappcoba.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)