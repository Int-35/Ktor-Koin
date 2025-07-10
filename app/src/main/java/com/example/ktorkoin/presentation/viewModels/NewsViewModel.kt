package com.example.ktorkoin.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.ktorkoin.data.dataSouce.local.models.NewsEntity
import com.example.ktorkoin.domain.Repo.LocalNewsRepo
import com.example.ktorkoin.domain.Repo.NewsRepo
import com.example.ktorkoin.data.dataSouce.network.NewsPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repo: NewsRepo,
    private val localNewsRepo: LocalNewsRepo
): ViewModel() {

    lateinit var  newsList: Flow<List<NewsEntity>>
      init {
          viewModelScope.launch {
              newsList = localNewsRepo.getNews()
          }
      }


     fun saveArticle(newsEntity: NewsEntity){
         viewModelScope.launch {
             localNewsRepo.insertNews(newsEntity)
         }
    }

    val newsPager = Pager(
        config = PagingConfig(10)
    ) {
        NewsPagingSource(repo)
    }.flow.cachedIn(viewModelScope)



}