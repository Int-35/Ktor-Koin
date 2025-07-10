package com.example.ktorkoin.data.dataSouce.network.models


import kotlinx.serialization.Serializable

@Serializable
data class News(
    val article_id: String,
    val title: String,
    val description: String? = null,
    val link: String,
    val pubDate: String
)
