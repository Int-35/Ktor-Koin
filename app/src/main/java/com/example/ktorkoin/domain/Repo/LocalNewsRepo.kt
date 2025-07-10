package com.example.ktorkoin.domain.Repo

import com.example.ktorkoin.data.dataSouce.local.NewsDao
import com.example.ktorkoin.data.dataSouce.local.models.NewsEntity
import kotlinx.coroutines.flow.Flow

open class LocalNewsRepo(
    private val dao: NewsDao
) {

    suspend fun insertNews(news: NewsEntity) {
        dao.upsert(news)
    }

   fun getNews(): Flow<List<NewsEntity>> {
        return dao.getAllNews()
    }

}
