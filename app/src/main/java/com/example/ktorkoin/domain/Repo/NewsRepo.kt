package com.example.ktorkoin.domain.Repo

import com.example.ktorkoin.Result
import com.example.ktorkoin.data.dataSouce.network.models.News

interface NewsRepo {
    suspend fun getNews(page: Int): Result<List<News>>
}