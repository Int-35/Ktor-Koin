package com.example.ktorkoin.data.dataSouce.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ktorkoin.data.model.Article


@Database(
    entities = [Article::class],
    version = 3,
    exportSchema = true,
    autoMigrations =[
        AutoMigration(from = 1 , to = 2),
        AutoMigration(from = 2, to = 3)
    ]
)
abstract class NewsDb: RoomDatabase() {
    abstract fun NewsDao(): NewsDao
}