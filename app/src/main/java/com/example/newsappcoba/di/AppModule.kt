package com.example.newsappcoba.di

import android.app.Application
import android.content.Context
import com.example.newsappcoba.BuildConfig
import com.example.newsappcoba.data.manager.LocalUserManagerImpl
import com.example.newsappcoba.domain.manager.LocalUserManager
import com.example.newsappcoba.domain.repository.NewsRepository
import com.example.newsappcoba.domain.repository.NewsRepositoryImpl
import com.example.newsappcoba.domain.usecase.app_entry.AppEntryUseCase
import com.example.newsappcoba.domain.usecase.app_entry.ReadAppEntry
import com.example.newsappcoba.domain.usecase.app_entry.SaveAppEntry
import com.example.newsappcoba.domain.usecase.news.GetNews
import com.example.newsappcoba.domain.usecase.news.NewsUseCases
import com.example.newsappcoba.domain.usecase.news.SearchNews
import com.example.newsappcoba.remote.NewsApi
import com.example.newsappcoba.utils.Constant.CONNECT_TIMEOUT
import com.example.newsappcoba.utils.Constant.READ_TIMEOUT
import com.example.newsappcoba.utils.Constant.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
    ) = AppEntryUseCase(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(okHttpClient: OkHttpClient) : NewsApi{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ) : NewsRepository = NewsRepositoryImpl(newsApi = newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository) : NewsUseCases{
        return  NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }
}