package com.example.ktorkoin.data.dataSouce.local.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Article")
data class NewsEntity(
    @PrimaryKey()
    val article_id: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("desp")
    val description: String? = null,
    @ColumnInfo("link")
    val link: String,
    @ColumnInfo("pubDate")
    val pubDate: String
)
