package com.example.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weatherapp.Forecast

class PageViewModel : ViewModel() {

    private val _forecast = MutableLiveData<Forecast>()

    val forecast: LiveData<Forecast> = Transformations.map(_forecast) {
        it
    }

    fun setForecast(forecast: Forecast) {
        _forecast.value = forecast
    }

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}



