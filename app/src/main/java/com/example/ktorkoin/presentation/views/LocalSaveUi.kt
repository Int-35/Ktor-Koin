package com.example.ktorkoin.presentation.views


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ktorkoin.data.model.Article
import com.example.ktorkoin.presentation.viewModels.NewsViewModel


@Composable
fun LocalSaveUi(viewModel: NewsViewModel) {
    val articleList: State<List<Article>> = viewModel.articleList.collectAsState(listOf())
    Scaffold {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
           items(articleList.value) {
               NewsCard(it,{})
           }
        }
    }

}



