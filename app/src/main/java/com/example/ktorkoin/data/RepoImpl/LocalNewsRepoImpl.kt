package com.example.ktorkoin.data.RepoImpl

import androidx.paging.PagingSource
import com.example.ktorkoin.data.dataSouce.local.NewsDao
import com.example.ktorkoin.data.model.Article
import com.example.ktorkoin.domain.Repo.LocalNewsRepo

class LocalNewsRepoImpl(
    private val dao: NewsDao
): LocalNewsRepo {

    override suspend fun upsertArticles(article: List<Article>) {
        article.forEach {
            dao.upsert(it)
        }
    }

    override fun getPagedArticles(): PagingSource<Int, Article> {
        return dao.getPagedNews()
    }

}