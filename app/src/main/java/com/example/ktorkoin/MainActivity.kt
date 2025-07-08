package com.example.ktorkoin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.ktorkoin.data.models.News
import com.example.ktorkoin.ui.theme.KtorKoinTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val viewModel = getViewModel<NewsViewModel>()
            val result = viewModel.news.collectAsState()
            KtorKoinTheme {
                result.value?.let {
                    when(it){
                        is Result.Failure -> {
                            Toast.makeText(context,it.exception.message, Toast.LENGTH_SHORT).show()
                        }
                        Result.Loading -> {
                            Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        }
                        is Result.Success -> {
                            NewsView(it.news)
                        }
                    }
                }

                }
            }
        }
    }


@Composable
fun NewsView(news: List<News>){

    Scaffold {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            items(news) {
                Text(it.title)
            }
        }
    }

}