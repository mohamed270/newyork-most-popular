package com.newyork.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ArticlesDto (
        @SerializedName("published_date")
        val publishedDate:String?,
        @SerializedName("title")
        val title:String?,
        @SerializedName("id")
        val serverId:String?,
        @SerializedName("abstract")
        val abstract:String,
        @SerializedName("byline")
        val author:String?

)