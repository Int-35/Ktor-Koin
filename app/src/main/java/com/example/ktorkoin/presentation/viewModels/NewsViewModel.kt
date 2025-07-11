package com.example.ktorkoin.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.ktorkoin.data.dataSouce.network.NewsPagingSource
import com.example.ktorkoin.data.model.Article
import com.example.ktorkoin.data.RepoImpl.LocalNewsRepoImpl
import com.example.ktorkoin.domain.Repo.LocalNewsRepo
import com.example.ktorkoin.domain.Repo.NetworkNewsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repo: NetworkNewsRepo,
    private val localNewsRepo: LocalNewsRepo
): ViewModel() {

    private val _article = MutableStateFlow<Article?>(null)
    val article: StateFlow<Article?> = _article

    fun getNetworkArticle(arti_id: String) {
        viewModelScope.launch {
            _article.value = repo.getArticle(arti_id)
        }
    }


    val newsPager = Pager(
        config = PagingConfig(10)
    ) {
        NewsPagingSource(repo , localNewsRepo)
    }.flow.cachedIn(viewModelScope)

    val LocalNewsPager = Pager(
        config = PagingConfig(10)
    ){
        localNewsRepo.getPagedArticles()
    }.flow.cachedIn(viewModelScope)

}