package com.example.ktorkoin

import com.example.ktorkoin.data.models.News

sealed class Result< out T> {
    data class Success<out T>(val news: List<News>): Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()
}