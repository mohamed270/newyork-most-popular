package com.newyork.data.remote.service

import com.google.gson.annotations.SerializedName
import com.newyork.data.remote.dto.ArticlesDto

data class NewsListResponse (

        @SerializedName("results")
        val articles:List<ArticlesDto>,
        )


