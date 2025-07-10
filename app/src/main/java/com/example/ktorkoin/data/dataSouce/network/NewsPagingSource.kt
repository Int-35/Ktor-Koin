package com.example.ktorkoin.data.dataSouce.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ktorkoin.Result
import com.example.ktorkoin.data.model.Article
import com.example.ktorkoin.domain.Repo.NetworkNewsRepo

class NewsPagingSource(
    private val repo: NetworkNewsRepo
): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val currentPage = params.key ?: 1
            val response = repo.getArticleList(currentPage)
            when (response) {
                is Result.Success -> {
                     val articleList: List<Article> = response.articles
                    LoadResult.Page(
                        data = articleList,
                        prevKey = if (currentPage == 1) null else currentPage - 1,
                        nextKey = if (articleList.isNotEmpty()) currentPage + 1 else null
                    )
                }
                is Result.Failure -> {
                    LoadResult.Error(response.exception)
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}