package com.example.mimblu.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://dev.mimblu.com/mimblu-yii2-1552/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val Service: mimbluApi by lazy {
        retrofit.create(mimbluApi::class.java)
    }
}