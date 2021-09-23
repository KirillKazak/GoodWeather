package com.example.goodweather.data.remote.retrofit

import com.example.goodweather.domain.entity.WeatherResponse
import com.example.goodweather.utill.Constants.Companion.API_KEY
import com.example.goodweather.utill.Constants.Companion.METRIC
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("/data/2.5/weather")
    fun getWeatherInformationByCityName(
        @Query("q")
        cityName: String = "London",
        @Query("appid")
        apiKey: String = API_KEY,
        @Query("units")
        units: String = METRIC
    ): Single<WeatherResponse>

    @GET("/data/2.5/weather")
    fun getWeatherInformationByCoordinates(
        @Query("lat")
        lat: Double = 0.0,
        @Query("lon")
        long: Double = 0.0,
        @Query("appid")
        apiKey: String = API_KEY,
        @Query("units")
        units: String = METRIC
    ): Single<WeatherResponse>
}