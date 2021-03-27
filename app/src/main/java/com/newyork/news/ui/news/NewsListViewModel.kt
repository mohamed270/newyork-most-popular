package com.newyork.news.ui.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.newyork.domain.model.newsModel.NewsModel
import com.newyork.domain.usecase.NewsUseCase
import com.newyork.news.R
import com.newyork.news.base.BaseViewModel
import kotlinx.coroutines.flow.collect

class NewsListViewModel @ViewModelInject constructor(
        private val newsUseCase: NewsUseCase
):BaseViewModel() {

    private val _newsLiveData = MutableLiveData<List<NewsModel>>()
    val newsLiveData: LiveData<List<NewsModel>> = _newsLiveData


    init {
        pullNews()
    }

    private  fun pullNews()= execute {
        showLoading()
        newsUseCase.pullNews()
        hideLoading()
        showSuccessMsg(R.string.success)
    }

       fun getNews()= execute {

        newsUseCase.getNews().collect {
            _newsLiveData.value=it
        }


    }


}