package com.example.portfolio.api

import com.example.portfolio.BuildConfig
import com.example.portfolio.data.NewsApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    suspend fun topHeadLines(
        @Query("country") country: String,
        @Query("apiKey") apiKey:String = BuildConfig.API_KEY
    ): Response<NewsApiResponse>

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"

        fun create(): NewsService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsService::class.java)
        }
    }
}