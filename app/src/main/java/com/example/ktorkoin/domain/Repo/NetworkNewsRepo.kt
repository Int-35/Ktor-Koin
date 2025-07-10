package com.example.ktorkoin.domain.Repo

import com.example.ktorkoin.Result
import com.example.ktorkoin.data.model.Article

interface NetworkNewsRepo {
    suspend fun getArticleList(page: Int): Result<List<Article>>
    suspend fun getArticle(article_id : String): Article?
}