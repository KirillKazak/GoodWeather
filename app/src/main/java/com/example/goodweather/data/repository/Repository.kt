package com.example.goodweather.data.repository

import com.example.goodweather.domain.entity.WeatherResponse
import io.reactivex.Single
import retrofit2.Response

interface Repository {
    fun getWeatherByCity (cityName: String) : Single<WeatherResponse>?
}