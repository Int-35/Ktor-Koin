package com.example.ktorkoin.di

import androidx.room.Room
import com.example.ktorkoin.data.RepoImpl.LocalNewsRepoImpl
import com.example.ktorkoin.domain.Repo.NetworkNewsRepo
import com.example.ktorkoin.data.RepoImpl.NetworkNewsRepoImpl
import com.example.ktorkoin.data.dataSouce.local.NewsDao
import com.example.ktorkoin.data.dataSouce.local.NewsDb
import com.example.ktorkoin.data.dataSouce.local.MIGRATION_1_2
import com.example.ktorkoin.data.dataSouce.local.MIGRATION_2_3
import com.example.ktorkoin.presentation.viewModels.NewsViewModel
import com.example.ktorkoin.data.dataSouce.network.ServiceClient
import com.example.ktorkoin.domain.Repo.LocalNewsRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ServiceClient().getHttpClient() }
    single<NetworkNewsRepo> { NetworkNewsRepoImpl( get() ) }
    single<LocalNewsRepo> { LocalNewsRepoImpl(get()) }
    viewModel <NewsViewModel>{ NewsViewModel(get(), get()) }

    single<NewsDb> {
        Room.databaseBuilder(
            get(),
            NewsDb::class.java,
            "contact_database"
        ).addMigrations(MIGRATION_1_2 , MIGRATION_2_3).build()
    }
    single<NewsDao> { get<NewsDb>().NewsDao() }
    single { LocalNewsRepoImpl(get()) }



}