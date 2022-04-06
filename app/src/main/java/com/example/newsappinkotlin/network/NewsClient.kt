package com.example.newsappinkotlin.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// build APIServices
object NewsClient {
    val BASE_URL="https://newsapi.org/"
    val service: APIServices
    init {
        val retrofit=
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        service =retrofit.create(APIServices::class.java)
    }
}