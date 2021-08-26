package com.example.goodweather.data.repository

import com.example.goodweather.data.remote.retrofit.ConfigureRetrofit
import com.example.goodweather.domain.entity.WeatherResponse
import io.reactivex.Single
import retrofit2.Response

class WeatherRepository : Repository {
    override suspend fun getWeatherByCity(cityName: String): Response<WeatherResponse> {
        return ConfigureRetrofit().weatherAPI.getWeatherInformationByCityName(cityName = cityName)
    }

    override fun getWeatherByLocationDetails(lat: Double, long: Double): Single<WeatherResponse> {
        return ConfigureRetrofit().weatherAPI.getWeatherInformationByCoordinates(lat=lat, long =long)
    }
}