package com.example.ktorkoin.data.dataSouce.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.ktorkoin.data.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Upsert
    suspend fun upsert(articleEntity: Article)

    @Delete
    suspend fun delete(articleEntity:Article )

    @Query("SELECT * FROM Article")
    fun getAllNews() : Flow<List<Article>>

    @Query("SELECT * FROM ARTICLE WHERE article_id = :articleId")
    suspend fun getArticleById(articleId: String): Article?

}