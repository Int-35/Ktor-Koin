package com.example.ktorkoin.presentation.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.ktorkoin.data.dataSouce.network.models.News
import com.example.ktorkoin.data.mapper.toEntity
import com.example.ktorkoin.presentation.viewModels.NewsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainUi(viewModel: NewsViewModel) {
    val newsList = viewModel.newsPager.collectAsLazyPagingItems()
    val show = remember { mutableStateOf(false) }

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                items(newsList.itemCount) { index ->
                    val item = newsList[index]
                    item?.let { newsItem ->
                        NewsCard(newsItem)
                        Spacer(modifier = Modifier.height(4.dp))

                        // Save button below each article
                        OutlinedButton(
                            onClick = { viewModel.saveArticle(newsItem.toEntity()) },
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text("Save Article")
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

                // Loader when loading new pages
                when (newsList.loadState.append) {
                    is LoadState.Loading -> {
                        item {
                            viewModel.viewModelScope.launch {
                                delay(3000)
                            }
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                    is LoadState.Error -> {
                        val e = newsList.loadState.append as LoadState.Error
                        item {
                            Text(
                                text = "Error loading more: ${e.error.localizedMessage}",
                                color = Color.Red,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                    else -> {}
                }
            }

            if (show.value) {
                LocalSaveUi(viewModel)
            }

            OutlinedButton(
                onClick = { show.value = true },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Click To Read Local Saved News")
            }
        }
    }
}


@Composable
fun NewsCard(
    news: News
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(news.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(news.description ?: "No description available", style = MaterialTheme.typography.bodyMedium)

        }
    }
}

