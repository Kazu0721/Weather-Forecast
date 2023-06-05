package com.example.weatherforecast

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.MainActivity.Companion.APIKEY
import com.example.weatherforecast.MainActivity.Companion.LANG
import com.example.weatherforecast.MainActivity.Companion.UNITS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForecastViewModel : ViewModel() {
    var cityArticle = MutableLiveData<MutableList<CityName>>()
    var article = MutableLiveData<MutableList<User>>()

    init {
        cityArticle.value = mutableListOf()
        article.value = mutableListOf()
    }

    suspend fun getForecast(cityName: String): List<User>? {

        viewModelScope.launch(Dispatchers.IO) {
            val api = ApiRepos().get()
            try {
                val response = api.getWeather(cityName, UNITS, LANG, APIKEY)

                val lists = response.list
                for (listItems in lists) {
                    val dates = listItems.dt_txt
                    val temp = listItems.main.temp
                    val humidity = listItems.main.humidity
                    val pressure = listItems.main.pressure
                    val descriptionLists = listItems.weather
                    for (descriptionItem in descriptionLists) {
                        val description = descriptionItem.description
                        val icon = descriptionItem.icon

                        val iconUrl = "https://openweathermap.org/img/w/$icon.png"

                        val addArticle = article.value ?: null
                        addArticle?.add(User(dates, description, temp, humidity, pressure, iconUrl))

                        article.postValue(addArticle)
                    }
                }
                val cities = response.city.name
                val cityName = cityArticle.value ?: null
                cityName?.add(CityName(cities))
                cityArticle.postValue(cityName)

            } catch (e: Exception) {

            }
        }.join()
        return null
    }
}