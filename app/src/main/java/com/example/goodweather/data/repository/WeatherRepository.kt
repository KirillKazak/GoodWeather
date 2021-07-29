package com.example.goodweather.data.repository

import com.example.goodweather.data.remote.retrofit.ConfigureRetrofit
import com.example.goodweather.domain.entity.WeatherResponse
import retrofit2.Response

class WeatherRepository : Repository {
    override suspend fun getWeatherByCity(cityName: String): Response<WeatherResponse>? {
        return ConfigureRetrofit.weatherAPI?.getWeatherInformation(cityName = cityName)
    }
}