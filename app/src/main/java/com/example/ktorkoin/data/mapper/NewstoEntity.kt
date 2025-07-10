package com.example.ktorkoin.data.mapper

import com.example.ktorkoin.data.dataSouce.local.models.NewsEntity
import com.example.ktorkoin.data.dataSouce.network.models.News

fun News.toEntity(): NewsEntity {
    return NewsEntity(
        article_id = article_id,
        title = title,
        description = description,
        link = link,
        pubDate = pubDate
    )
}
