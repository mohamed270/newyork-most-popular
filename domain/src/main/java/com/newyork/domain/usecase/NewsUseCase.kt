package com.newyork.domain.usecase

import com.newyork.domain.BaseUseCase
import com.newyork.domain.model.newsModel.NewsModel
import com.newyork.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsUseCase @Inject constructor(
        private val newsRepository:INewsRepository
):BaseUseCase() {

       suspend fun  getNews():Flow<List<NewsModel>> = execute {
            return@execute newsRepository.getNews()
       }


    suspend fun pullNews() = execute {

        val list= newsRepository.pullNews()

    }

}