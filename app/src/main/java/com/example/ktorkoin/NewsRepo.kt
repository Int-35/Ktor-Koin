package com.example.ktorkoin

import com.example.ktorkoin.data.models.News


interface NewsRepo {
    suspend fun getNews(): Result<List<News>>
}