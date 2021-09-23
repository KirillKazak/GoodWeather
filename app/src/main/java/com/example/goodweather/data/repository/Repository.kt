package com.example.goodweather.data.repository

import com.example.goodweather.domain.entity.NewWeatherResponse
import io.reactivex.Single


interface Repository {
    fun getWeatherByCity(cityName: String): Single<NewWeatherResponse>?

    fun getWeatherByLocationDetails(lat: Double, long: Double): Single<NewWeatherResponse>
}