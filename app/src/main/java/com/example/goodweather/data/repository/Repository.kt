package com.example.goodweather.data.repository

import com.example.goodweather.domain.entity.WeatherResponse
import io.reactivex.Single


interface Repository {
    fun getWeatherByCity(cityName: String): Single<WeatherResponse>?

    fun getWeatherByLocationDetails(lat: Double, long: Double): Single<WeatherResponse>
}