package com.example.wizeline

import com.example.wizeline.data.datasource.RemoteDataSourceImpl
import com.example.wizeline.data.datasource.models.AvailableBooksResponse
import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.data.service.ApiSuccess
import com.example.wizeline.data.service.BaseService
import com.example.wizeline.data.service.GenericInterceptor
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit


class RetrofitTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .addInterceptor(GenericInterceptor())
        .build()

    private val service = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create<BaseService>()

    private val networkDataSource = RemoteDataSourceImpl(service)

    @Before
    fun init() {

    }

    @Test
    fun `test response is successful`() {
        mockWebServer.enqueueResponse(200, "get-books.json")
        runBlocking {
            val actual = networkDataSource.getAvailableBooks()
            val expected = AvailableBooksResponse(
                    success = true,
                    payload = arrayListOf(BookInfoEntity(
                        book = "btc_mxn",
                        minimumAmount = ".003",
                        maximumAmount = "1000.00",
                        minimumPrice = "100.00",
                        maximumPrice =  "1000000.00",
                        minimumValue =  "25.00",
                        maximumValue = "1000000.00")
                    )
                )
            assert(actual == expected)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}

fun MockWebServer.enqueueResponse(code: Int, file: String) {
    val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$file")
    val source = inputStream?.source()?.buffer()
    source?.let {
        enqueue(
            MockResponse().apply {
                setResponseCode(code)
                setBody(source.readString(StandardCharsets.UTF_8))
            }
        )
    }
}