package com.example.ktorkoin.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.ktorkoin.R
import com.example.ktorkoin.data.model.Article
import com.example.ktorkoin.navigation.Routes
import com.example.ktorkoin.presentation.viewModels.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUi(viewModel: NewsViewModel, navHostController: NavHostController) {
    val newsList = viewModel.newsPager.collectAsLazyPagingItems()
    val showLocal = remember { mutableStateOf(false) }

    val isNetworkError = newsList.loadState.refresh is LoadState.Error
    val isListEmpty = newsList.itemCount == 0
    val notLoading = newsList.loadState.refresh !is LoadState.Loading

    val shouldShowLocal = showLocal.value || (isNetworkError && isListEmpty && notLoading)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("News Feed") })
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (shouldShowLocal) {
                LocalSaveUi(viewModel, navHostController)
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(newsList.itemCount) { index ->
                            val item = newsList[index]
                            item?.let { article ->
                                NewsCard(article) {
                                    navHostController.navigate(Routes.DetailScreenRoute(article.articleId))
                                }
                            }
                        }

                        if (newsList.loadState.append is LoadState.Loading) {
                            item(span = { GridItemSpan(2) }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }
            }

            // ✅ Floating "Saved News" Button at Bottom-Right
            Button(
                onClick = { showLocal.value = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                shape = RoundedCornerShape(50), // pill style
            ) {
               Text(
                   text = "Saved News",
                   color = Color.White
               )
            }

        }
    }
}



@Composable
fun NewsCard(
    article: Article,
    onClick: @Composable () -> Unit
) {
    val clicked = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clickable { clicked.value = true },
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(6.dp)
    ) {
        if (clicked.value) {
            onClick()
        }

        Column {
            if (article.image_url.isNullOrEmpty()) {
                // 🔄 Show default image
                Image(
                    painter = painterResource(id = R.drawable.default_img),
                    contentDescription = "Default image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                // 🌐 Load from URL
                AsyncImage(
                    model = article.image_url,
                    contentDescription = article.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.default_img)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = article.title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                maxLines = 2
            )
        }
    }
}
