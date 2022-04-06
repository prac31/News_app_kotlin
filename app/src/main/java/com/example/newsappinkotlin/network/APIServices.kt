package com.example.newsappinkotlin.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {
    @GET("v2/top-headlines")

    fun getAllHeadlines(@Query("apiKey") apiKey:String="821637ad22f64a4d848a27ea7cb1110b",@Query("page") pageNumber:Int=1
                        ,@Query("country") country:String="us")
            :Call<NewsResponse>
}