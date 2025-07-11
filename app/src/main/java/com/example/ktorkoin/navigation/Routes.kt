package com.example.ktorkoin.navigation

import kotlinx.serialization.Serializable

sealed class Routes{

    @Serializable
    object MainUiRoute

    @Serializable
    data class DetailScreenRoute( val article_id: String)

    @Serializable
    object LocalSaveUiRoute
}