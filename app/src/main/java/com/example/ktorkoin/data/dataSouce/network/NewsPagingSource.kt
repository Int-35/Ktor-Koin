package com.example.ktorkoin.data.dataSouce.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ktorkoin.domain.Repo.NewsRepo
import com.example.ktorkoin.Result
import com.example.ktorkoin.data.dataSouce.network.models.News

class NewsPagingSource(
    private val repo: NewsRepo
): PagingSource<Int, News>() {
    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        return try {
            val currentPage = params.key ?: 1
            val response = repo.getNews(currentPage)
            when (response) {
                is Result.Success -> {
                     val newsList: List<News> = response.news
                    LoadResult.Page(
                        data = newsList,
                        prevKey = if (currentPage == 1) null else currentPage - 1,
                        nextKey = if (newsList.isNotEmpty()) currentPage + 1 else null
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