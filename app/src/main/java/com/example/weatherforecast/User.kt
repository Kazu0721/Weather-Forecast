package com.example.weatherforecast

import java.util.*

data class User(
    val date: String,
    val description: String,
    val temp: Double,
    val humidity: Int,
    val pressure: Int,
    val icon: String
    ) {
    val id = UUID.randomUUID().toString()
}
data class CityName(val cityName: String){
    val id = UUID.randomUUID().toString()
}