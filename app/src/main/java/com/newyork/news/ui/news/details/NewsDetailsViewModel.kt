package com.newyork.news.ui.news.details

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.newyork.news.base.BaseViewModel
import kotlin.math.abs

class NewsDetailsViewModel @ViewModelInject constructor(
        @Assisted private val savedStateHandle: SavedStateHandle

):BaseViewModel() {

    private val abstract: String? = savedStateHandle[NewsDetailsActivity.KEY_ABSTRACT]


    fun getAbstract(): String? {
        return abstract
    }
}