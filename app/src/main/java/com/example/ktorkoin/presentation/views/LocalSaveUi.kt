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
import com.example.ktorkoin.data.dataSouce.local.models.NewsEntity
import com.example.ktorkoin.data.mapper.toDomain
import com.example.ktorkoin.presentation.viewModels.NewsViewModel


@Composable
fun LocalSaveUi(viewModel: NewsViewModel) {
    val newsList: State<List<NewsEntity>> = viewModel.newsList.collectAsState(listOf())
    Scaffold {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
           items(newsList.value) {
               NewsCard(it.toDomain())
           }
        }
    }

}



