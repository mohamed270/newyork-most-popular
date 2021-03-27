package com.newyork.data.remote.service

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("emailed/7.json")
    suspend fun getPopularNews( @Query("api-key")
                                    apikey: String?):NewsListResponse
}