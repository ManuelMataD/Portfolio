package com.example.portfolio

import com.example.portfolio.api.NewsService
import com.example.portfolio.repository.ApiKeyInvalidException
import com.example.portfolio.repository.MissingApiKeyException
import com.example.portfolio.repository.NewsRepositoryImp
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

class NewsRepositoryTest {
    private val mockWebServer = MockWebServer()

    private val newsService = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsService::class.java)

    private val newsRepository = NewsRepositoryImp(newsService)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Top headlines response is correct`() {
        mockWebServer.enqueueResponse("top_headlines.json")

        runBlocking {
            val articles = newsRepository.getNews("US")
            assertEquals(2, articles.size)
            assertEquals("Lambert Strether", articles[0].author)
            assertEquals("Matt Posky", articles[1].author)
        }
    }

    @Test
    fun `Api key missing exception`() {
        mockWebServer.enqueueResponse("api_key_missing.json")
        assertThrows(MissingApiKeyException::class.java) {
            runBlocking {
                newsRepository.getNews("US")
            }
        }
    }

    @Test
    fun `Invalid Api key exception`() {
        mockWebServer.enqueueResponse("api_key_invalid.json")
        assertThrows(ApiKeyInvalidException::class.java) {
            runBlocking {
                newsRepository.getNews("US")
            }
        }
    }
}

fun MockWebServer.enqueueResponse(filePath: String) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(it.readString(StandardCharsets.UTF_8))
        )
    }
}