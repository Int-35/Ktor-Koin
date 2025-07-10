package com.example.ktorkoin.data.RepoImpl

import com.example.ktorkoin.Result
import com.example.ktorkoin.data.model.Article
import com.example.ktorkoin.data.model.NewsResponse
import com.example.ktorkoin.data.dataSouce.network.crendential.Api_Key
import com.example.ktorkoin.data.dataSouce.network.crendential.Base_Url
import com.example.ktorkoin.domain.Repo.NetworkNewsRepo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class NetworkNewsRepoImpl(
    private val client: HttpClient,
) : NetworkNewsRepo {

    override suspend fun getArticleList(page: Int): Result<List<Article>> {
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

    override suspend fun getArticle(articleId: String): Article? {
        return try {
            val response: NewsResponse = client.get("https://newsdata.io/api/1/latest") {
                parameter("apikey", Api_Key)
                parameter("id", articleId)
            }.body()

            response.results?.firstOrNull()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}