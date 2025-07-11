package com.example.ktorkoin.presentation.views


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.ktorkoin.navigation.Routes
import com.example.ktorkoin.presentation.viewModels.NewsViewModel

@Composable
fun LocalSaveUi(
    viewModel: NewsViewModel,
    navHostController: NavHostController
) {
    val localArticles =  viewModel.LocalNewsPager.collectAsLazyPagingItems()

    if (localArticles.itemCount == 0) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No saved articles available")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(localArticles.itemCount) {
                val article = localArticles[it]
                article?.let {
                    NewsCard(article) { navHostController.navigate(Routes.LocalSaveUiRoute) }
                }
            }
        }
    }
}




