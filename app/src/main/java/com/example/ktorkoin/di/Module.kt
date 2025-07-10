package com.example.ktorkoin.di

import androidx.room.Room
import com.example.ktorkoin.domain.Repo.LocalNewsRepo
import com.example.ktorkoin.domain.Repo.NewsRepo
import com.example.ktorkoin.data.RepoImpl.NewsRepoImpl
import com.example.ktorkoin.data.dataSouce.local.NewsDao
import com.example.ktorkoin.data.dataSouce.local.NewsDb
import com.example.ktorkoin.data.dataSouce.local.models.MIGRATION_1_2
import com.example.ktorkoin.presentation.viewModels.NewsViewModel
import com.example.ktorkoin.data.dataSouce.network.ServiceClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ServiceClient().getHttpClient() }
    single<NewsRepo> { NewsRepoImpl( get(), get() ) }
    viewModel <NewsViewModel>{ NewsViewModel(get(), get()) }

    single<NewsDb> {
        Room.databaseBuilder(
            get(),
            NewsDb::class.java,
            "contact_database"
        ).addMigrations(MIGRATION_1_2).build()
    }
    single<NewsDao> { get<NewsDb>().NewsDao() }
    single { LocalNewsRepo(get()) }



}