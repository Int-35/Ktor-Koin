package com.example.ktorkoin

import com.example.ktorkoin.data.model.Article

sealed class Result< out T> {
    data class Success<out T>(val articles: List<Article>): Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()
}