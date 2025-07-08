package com.example.ktorkoin

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorkoin.data.models.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repo: NewsRepo
): ViewModel() {

    private val _news =  MutableStateFlow<Result<List<News>>?>(null)
    val news: StateFlow<Result<List<News>>?> = _news

    init {
        viewModelScope.launch {
            _news.value = Result.Loading
            _news.value = repo.getNews()
        }
    }
}