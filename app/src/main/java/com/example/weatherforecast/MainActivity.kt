package com.example.weatherforecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val listAdapter = WeatherAdapter()
    private val cityAdapter = CityAdapter()

    companion object{
        const val BASE_URL = "https://api.openweathermap.org"
        const val UNITS = "metric"
        const val LANG = "ja"
        const val APIKEY = "a888da2f7ec331134511bf03ff6b8228"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[ForecastViewModel::class.java]
        val bindingRoot = binding.root
        setContentView(bindingRoot)

        val concatAdapter = ConcatAdapter().apply {
            addAdapter(cityAdapter)
            addAdapter(listAdapter)
        }

        binding.weatherlist.adapter = concatAdapter
        binding.weatherlist.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)

        viewModel.cityArticle.observe(this, Observer { newCity ->
            cityAdapter.submitList(newCity)
            cityAdapter.notifyDataSetChanged()
        })

        viewModel.article.observe(this, Observer<MutableList<User>> { newData ->
            listAdapter.submitList(newData)
            listAdapter.notifyDataSetChanged()
        })

        binding.forecastbutton.setOnClickListener{
            viewModel.cityArticle.value?.clear()
            viewModel.article.value?.clear()

            runBlocking{
                launch {
                    val cityName = binding.cityname.text.toString()
                    viewModel.getForecast(cityName)
                    binding.cityname.text.clear()
                }
            }

        }
    }
}