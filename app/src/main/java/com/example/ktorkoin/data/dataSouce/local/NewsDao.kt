package com.example.ktorkoin.data.dataSouce.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.ktorkoin.data.dataSouce.local.models.NewsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Upsert
    suspend fun upsert(newsEntity: NewsEntity)

    @Delete
    suspend fun delete(newsEntity: NewsEntity)

    @Query("SELECT * FROM Article")
    fun getAllNews() : Flow<List<NewsEntity>>

}