package com.example.ktorkoin.data.dataSouce.local.models

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS Article (
                article_id TEXT NOT NULL PRIMARY KEY,
                desp TEXT,
                link TEXT NOT NULL,
                title TEXT NOT NULL,
                pubDate TEXT NOT NULL
            )
        """.trimIndent())
    }
}