package com.news.app.network

import com.news.app.pojo.NewsResponse
import com.news.app.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getNewsByCountry(
        @QueryMap params:HashMap<String,Any>
    ):Response<NewsResponse>


    @GET("v2/everything")
    suspend fun getNewsBySeach(
        @Query("q")query: String,
        @Query("pageSize")pageSize: Int=100,
        @Query("apiKey")apiKey: String="${Constants.API_KEY}"
    ):Response<NewsResponse>
}