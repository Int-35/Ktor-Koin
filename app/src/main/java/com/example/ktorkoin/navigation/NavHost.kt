package com.example.ktorkoin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ktorkoin.presentation.viewModels.NewsViewModel
import com.example.ktorkoin.presentation.views.MainUi
import com.example.ktorkoin.presentation.views.DetailScreen
import com.example.ktorkoin.presentation.views.LocalSaveUi
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavHost(){

    val viewModel = koinViewModel<NewsViewModel>()
    val navHostController = rememberNavController()

    NavHost( navController = navHostController, startDestination = Routes.MainUiRoute) {

        composable<Routes.MainUiRoute> {
            MainUi(viewModel = viewModel , navHostController = navHostController)
        }

        composable<Routes.DetailScreenRoute> {
            val article_id = it.toRoute<Routes.DetailScreenRoute>().article_id
            DetailScreen(
                articleId = article_id,
                viewModel = koinViewModel(),
                navHostController = navHostController
            )
        }

        composable<Routes.LocalSaveUiRoute> {
            LocalSaveUi(
                viewModel = viewModel,
                navHostController = navHostController
            )
        }



    }
}