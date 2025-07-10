package com.example.ktorkoin.domain.Repo

import com.example.ktorkoin.data.dataSouce.local.NewsDao
import com.example.ktorkoin.data.model.Article
import kotlinx.coroutines.flow.Flow

open class LocalNewsRepo(
    private val dao: NewsDao
) {

    suspend fun insertNews(article: Article) {
        dao.upsert(article)
    }

    suspend fun getArticleById( article_id : String ): Article?{
             return dao.getArticleById(article_id)
    }

   fun getNews(): Flow<List<Article>> {
        return dao.getAllNews()
    }

}
