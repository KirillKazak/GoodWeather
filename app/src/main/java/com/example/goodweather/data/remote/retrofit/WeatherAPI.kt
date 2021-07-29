package com.example.goodweather.data.remote.retrofit

import com.example.goodweather.domain.entity.WeatherResponse
import com.example.goodweather.utill.Constants.Companion.API_KEY
import com.example.goodweather.utill.Constants.Companion.METRIC
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("2.5/weather")
    fun getWeatherInformation(
        @Query ("q")
        cityName: String = "",
        @Query ("appid")
        apiKey: String = API_KEY,
        @Query ("units")
        units: String = METRIC
    ) : Response<WeatherResponse>
}