package com.example.portfolio.data

import com.google.gson.annotations.SerializedName

data class NewsApiResponse (
    @field:SerializedName("status") val status: String,
    @field:SerializedName("code") val code: String,
    @field:SerializedName("articles") val articles: List<News>
)