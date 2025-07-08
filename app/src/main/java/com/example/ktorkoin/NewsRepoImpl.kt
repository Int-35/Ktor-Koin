package com.example.ktorkoin

import com.example.ktorkoin.crendential.Api_Key
import com.example.ktorkoin.crendential.Base_Url
import com.example.ktorkoin.data.models.News
import com.example.ktorkoin.data.models.NewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter


class NewsRepoImpl(
    private val client: HttpClient
) : NewsRepo {
    override suspend fun getNews(): Result<List<News>> {
        return try {
            val response: NewsResponse = client.get(Base_Url){
                parameter("apikey", Api_Key)
                parameter("q", "bitcoin")
            }.body()
            Result.Success(response.results)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}