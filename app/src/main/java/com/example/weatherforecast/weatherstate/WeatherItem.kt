package com.example.weatherforecast.weatherstate



data class WeatherItem(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherData>,
    val message: Int
)