package com.newyork.data.repository

import com.newyork.data.BaseRepository
import com.newyork.data.Util.Utils
import com.newyork.data.local.database.Article.ArticleDao
import com.newyork.data.mapper.toEntity
import com.newyork.data.mapper.toModel
import com.newyork.data.remote.service.NewsApiService
import com.newyork.domain.model.newsModel.NewsModel
import com.newyork.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepository @Inject constructor(
        private val newsApiService: NewsApiService,
        private val articleDao: ArticleDao
):BaseRepository(),INewsRepository {
    override suspend fun getNews():Flow<List<NewsModel>> = execute {
        return@execute articleDao.getNews().map { it.map { it.toModel() } }
    }


    override suspend fun pullNews() =execute {


        val news = newsApiService.getPopularNews(Utils.API_KEY).articles.map {
            it.toEntity()
        }

        if(news.isNotEmpty()) {
            articleDao.clear()
            articleDao.insert(news)
        }



    }


}