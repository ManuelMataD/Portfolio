    package com.example.portfolio.di

import com.example.portfolio.api.NewsService
import com.example.portfolio.repository.NewsRepository
import com.example.portfolio.repository.NewsRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    
    @Singleton
    @Provides
    fun provideNewsService(service: NewsService): NewsRepository =
        NewsRepositoryImp(service)
}