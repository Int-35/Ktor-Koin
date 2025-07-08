package com.example.ktorkoin.di

import com.example.ktorkoin.NewsRepo
import com.example.ktorkoin.NewsRepoImpl
import com.example.ktorkoin.NewsViewModel
import com.example.ktorkoin.data.network.ServiceClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ServiceClient().getHttpClient() }
    single<NewsRepo> { NewsRepoImpl(get()) }
    viewModel <NewsViewModel>{ NewsViewModel(get()) }
}