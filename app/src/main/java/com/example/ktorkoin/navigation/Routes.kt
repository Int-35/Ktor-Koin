package com.example.ktorkoin.navigation

import android.R
import kotlinx.serialization.Serializable

sealed class Routes{

    @Serializable
    object MainUiRoute

    @Serializable
    data class DetailScreenRoute( val article_id: String)
}