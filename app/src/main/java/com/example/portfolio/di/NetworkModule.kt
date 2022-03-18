package com.example.portfolio.di

import com.example.portfolio.api.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    
    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        return NewsService.create()
    }
}