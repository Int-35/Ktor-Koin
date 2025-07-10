package com.example.ktorkoin.data.dataSouce.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ktorkoin.data.dataSouce.local.models.NewsEntity


@Database(
    entities = [NewsEntity::class],
    version = 2,
    exportSchema = true,
    autoMigrations =[
        AutoMigration(from = 1 , to = 2)
    ]
)
abstract class NewsDb: RoomDatabase() {
    abstract fun NewsDao(): NewsDao
}