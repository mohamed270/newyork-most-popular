package com.newyork.data.mapper

import com.newyork.data.local.database.Article.ArticleEntity
import com.newyork.data.remote.dto.ArticlesDto
import com.newyork.domain.model.newsModel.NewsModel


fun ArticlesDto.toEntity()=ArticleEntity(
        abstractDetails= abstract,
        author=author,
        publishedDate=publishedDate,
        title=title

)


fun ArticleEntity.toModel()=NewsModel(
        author=author,
        publishedDate=publishedDate,
        title=title,
        abstract=abstractDetails,
        localId=localId
)