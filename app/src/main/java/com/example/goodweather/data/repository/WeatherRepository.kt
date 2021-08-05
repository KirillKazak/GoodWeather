package com.example.goodweather.data.repository

import com.example.goodweather.data.remote.retrofit.ConfigureRetrofit
import com.example.goodweather.domain.entity.WeatherResponse
import com.example.goodweather.utill.Constants
import io.reactivex.Single
import retrofit2.Response

class WeatherRepository : Repository {
    override fun getWeatherByCity(cityName: String): Single<WeatherResponse> {
        return ConfigureRetrofit().weatherAPI.getWeatherInformation(cityName = cityName)
    }
}