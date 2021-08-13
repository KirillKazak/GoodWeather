package com.example.goodweather.presentation.viewmodel.factorys

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodweather.data.repository.WeatherRepository
import com.example.goodweather.presentation.viewmodel.WeatherTodayViewModel

class ProvideFactoryWeatherToday (private val context: Application)
    : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WeatherTodayViewModel(context) as T
    }
}