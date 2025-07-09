package com.example.ktorkoin.data.models


import kotlinx.serialization.Serializable

@Serializable
data class News(
    val title: String,
    val description: String? = null,
    val link: String,
    val pubDate: String
)
