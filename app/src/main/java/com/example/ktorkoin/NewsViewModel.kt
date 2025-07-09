package com.example.ktorkoin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.ktorkoin.pagination.NewsPagingSource

class NewsViewModel(
    private val repo: NewsRepo
): ViewModel() {

    val newsPager = Pager(
        config = PagingConfig(10)
    ){
        NewsPagingSource(repo)
    }.flow.cachedIn(viewModelScope)

}