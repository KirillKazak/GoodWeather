package com.example.goodweather.presentation.viewmodel.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodweather.data.remote.retrofit.ConfigureRetrofit
import com.example.goodweather.data.repository.WeatherRepository
import com.example.goodweather.presentation.viewmodel.WeatherTodayViewModel

class ProvideFactoryWeatherToday (private val weatherRepository: WeatherRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherTodayViewModel(weatherRepository) as T
    }
}