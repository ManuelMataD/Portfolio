package com.example.portfolio.repository

import com.example.portfolio.api.NewsService
import com.example.portfolio.data.News
import java.lang.Exception
import javax.inject.Inject

interface NewsRepository {
    suspend fun getNews(country: String) : List<News>
    fun getNew(title: String): News
}

class NewsRepositoryImp @Inject constructor(private val service: NewsService): NewsRepository {
    private var news: List<News> = emptyList()

    override suspend fun getNews(country: String): List<News> {
        val apiResponse = service.topHeadLines(country).body()
        if (apiResponse?.status == "error") {
            when (apiResponse.code) {
                "apiKeyMissing" -> throw MissingApiKeyException()
                "apiKeyInvalid" -> throw ApiKeyInvalidException()
                else -> throw Exception()
            }
        }
        news = apiResponse?.articles ?: emptyList()
        return news
    }

    override fun getNew(title: String): News =
        news.first { it.title == title }
}

class MissingApiKeyException : java.lang.Exception()
class ApiKeyInvalidException : java.lang.Exception()