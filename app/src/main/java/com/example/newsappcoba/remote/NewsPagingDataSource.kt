package com.example.newsappcoba.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsappcoba.BuildConfig
import com.example.newsappcoba.domain.model.Article


class NewsPagingDataSource(
    private val newsApi: NewsApi,
    private val sources: String
) : PagingSource<Int, Article>() {


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        val myApiKey =BuildConfig.API_KEY
        return try {
            val newsResponse = newsApi.getNews(sources = sources, page = page,myApiKey)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title } //Remove duplicates

            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}