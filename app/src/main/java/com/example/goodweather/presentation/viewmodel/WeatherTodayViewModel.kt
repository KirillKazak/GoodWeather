package com.example.goodweather.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodweather.data.repository.WeatherRepository
import com.example.goodweather.domain.entity.WeatherResponse
import kotlinx.coroutines.launch

class WeatherTodayViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    val weatherInformationLifeData : MutableLiveData<WeatherResponse> = MutableLiveData()

    fun getWeatherByCity (cityName : String) = viewModelScope.launch {
        val response = weatherRepository.getWeatherByCity(cityName)
        val body = response?.body()
        weatherInformationLifeData.postValue(body)
        Log.d("TAG", weatherInformationLifeData.value?.name.toString())
    }
}