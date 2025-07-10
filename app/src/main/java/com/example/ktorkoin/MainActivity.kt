package com.example.ktorkoin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ktorkoin.navigation.NavHost
import com.example.ktorkoin.presentation.views.MainUi
import com.example.ktorkoin.ui.theme.KtorKoinTheme
import com.example.ktorkoin.presentation.viewModels.NewsViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            KtorKoinTheme {
                NavHost()
            }
        }
    }
}


