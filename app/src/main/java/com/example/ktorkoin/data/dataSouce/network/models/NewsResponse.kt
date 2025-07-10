package com.example.ktorkoin.data.dataSouce.network.models

import kotlinx.serialization.Serializable


@Serializable
data class NewsResponse(

    val status: String,

    val totalResults: Int,

    val results: List<News>,

    val nextPage: String?
)