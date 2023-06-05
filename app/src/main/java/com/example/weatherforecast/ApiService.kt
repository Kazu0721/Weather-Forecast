package com.example.weatherforecast

import com.example.weatherforecast.weatherstate.WeatherItem

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/data/2.5/forecast?")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appId: String
    ): WeatherItem
}