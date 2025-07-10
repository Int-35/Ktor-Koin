package com.example.ktorkoin.data.model

import kotlinx.serialization.Serializable


@Serializable
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val results: List<Article>,
    val nextPage: String?
)
