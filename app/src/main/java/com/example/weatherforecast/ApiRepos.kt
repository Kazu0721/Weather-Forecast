package com.example.weatherforecast

import com.example.weatherforecast.MainActivity.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class ApiRepos {
    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
     fun get(): ApiService{
        val retrofit = getRetrofit()
        return retrofit.create(ApiService::class.java)
    }
}