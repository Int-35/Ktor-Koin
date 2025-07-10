package com.example.ktorkoin.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.ktorkoin.data.model.Article
import com.example.ktorkoin.data.dataSouce.network.NewsPagingSource
import com.example.ktorkoin.domain.Repo.LocalNewsRepo
import com.example.ktorkoin.domain.Repo.NetworkNewsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repo: NetworkNewsRepo,
    private val localNewsRepo: LocalNewsRepo
): ViewModel() {

    private val _article = MutableStateFlow<Article?>(null)
    val article: StateFlow<Article?> = _article

    lateinit var  articleList: Flow<List<Article>>
      init {
          viewModelScope.launch {
              articleList = localNewsRepo.getNews()
          }
      }

    fun getArticle(arti_id: String) {
        viewModelScope.launch {
//            val article = localNewsRepo.getArticleById(arti_id)
            val article = repo.getArticle(arti_id)
            _article.value = article
        }
    }

     fun saveArticle(articleEntity: Article){
         viewModelScope.launch {
             localNewsRepo.insertNews(articleEntity)
         }
    }

    val newsPager = Pager(
        config = PagingConfig(10)
    ) {
        NewsPagingSource(repo)
    }.flow.cachedIn(viewModelScope)



}