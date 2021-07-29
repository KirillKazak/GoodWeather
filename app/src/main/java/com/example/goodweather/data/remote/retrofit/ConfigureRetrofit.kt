package com.example.goodweather.data.remote.retrofit

import com.example.goodweather.utill.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConfigureRetrofit {

    companion object {
        val retrofit: Retrofit? by lazy {
            val httpLongInterceptor = HttpLoggingInterceptor()
            httpLongInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLongInterceptor)
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val weatherAPI : WeatherAPI? = retrofit?.create(WeatherAPI:: class.java)
    }


}