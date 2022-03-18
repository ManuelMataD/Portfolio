package com.example.portfolio.data

import com.google.gson.annotations.SerializedName

data class News (
    @field:SerializedName("title") val title: String,
    @field:SerializedName("content") val content: String,
    @field:SerializedName("author") val author: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("urlToImage") val urlToImage: String
)