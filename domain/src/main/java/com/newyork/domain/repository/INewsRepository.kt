package com.newyork.domain.repository

import androidx.paging.PagingData
import com.newyork.domain.model.newsModel.NewsModel
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    suspend fun getNews(): Flow<List<NewsModel>>


    suspend fun pullNews()

}