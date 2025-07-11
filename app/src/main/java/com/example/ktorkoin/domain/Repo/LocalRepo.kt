package com.example.ktorkoin.domain.Repo

import androidx.paging.PagingSource
import com.example.ktorkoin.data.model.Article

interface LocalNewsRepo {
    suspend fun upsertArticles(article: List<Article>)
    fun getPagedArticles(): PagingSource<Int, Article>
}
