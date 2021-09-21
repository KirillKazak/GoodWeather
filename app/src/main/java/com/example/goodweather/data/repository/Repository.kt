package com.example.goodweather.data.repository

import com.example.goodweather.domain.entity.WeatherResponse
import com.example.goodweather.newentity.NewWeatherResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response


interface Repository {
    fun getWeatherByCity(cityName: String): Single<NewWeatherResponse>?

    fun getWeatherByLocationDetails(lat: Double, long: Double): Single<NewWeatherResponse>
}