package com.example.goodweather.data.repository

import com.example.goodweather.data.remote.retrofit.ConfigureRetrofit
import com.example.goodweather.domain.entity.NewWeatherResponse
import io.reactivex.Single

class WeatherRepository : Repository {
    override fun getWeatherByCity(cityName: String): Single<NewWeatherResponse> {
        return ConfigureRetrofit().weatherAPI.getWeatherInformationByCityName(cityName = cityName)
    }

    override fun getWeatherByLocationDetails(lat: Double, long: Double): Single<NewWeatherResponse> {
        return ConfigureRetrofit().weatherAPI.getWeatherInformationByCoordinates(
            lat = lat,
            long = long
        )
    }
}