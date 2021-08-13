package com.example.goodweather.data.repository

import com.example.goodweather.domain.entity.WeatherResponse
import io.reactivex.Single
import retrofit2.Response

interface Repository {
    suspend fun getWeatherByCity (cityName: String) : Response<WeatherResponse>?

    suspend fun  getWeatherByLocationDetails (lat : Double, long : Double) : Response<WeatherResponse>
}