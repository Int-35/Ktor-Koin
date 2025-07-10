package com.example.ktorkoin.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "Article")
data class Article(
    @PrimaryKey
    @SerialName("article_id")
    @ColumnInfo(name = "article_id")
    val articleId: String,

    @SerialName("desp")
    @ColumnInfo(name = "desp")
    val desp: String? = null,

    @SerialName("link")
    @ColumnInfo(name = "link")
    val link: String,

    @SerialName("title")
    @ColumnInfo(name = "title")
    val title: String,

    @SerialName("pubDate")
    @ColumnInfo(name = "pubDate")
    val pubDate: String,

    @SerialName("image_url")
    @ColumnInfo(name = "image_url")
    val image_url: String? = null
)
