package com.example.goodweather.presentation.viewmodel

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goodweather.data.remote.retrofit.ConfigureRetrofit
import com.example.goodweather.data.remote.retrofit.WeatherAPI
import com.example.goodweather.data.repository.WeatherRepository
import com.example.goodweather.domain.entity.WeatherResponse
import com.example.goodweather.location.GPSLocationInterface
import com.google.android.gms.location.FusedLocationProviderClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherTodayViewModel() : ViewModel()  {
    val weatherInformationLifeData : MutableLiveData<WeatherResponse> = MutableLiveData()
//
//    fun getWeatherByCity (cityName : String) = viewModelScope.launch {
//        val response = weatherRepository.getWeatherByCity(cityName)
//        val body = response?.body()
//        weatherInformationLifeData.postValue(body)
//        Log.d("TAG", weatherInformationLifeData.value?.name.toString())
//    }

    private val compositeDisposable = CompositeDisposable()


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

     fun fetchWeatherList(weatherRepository: WeatherRepository) {
        compositeDisposable.add(weatherRepository.getWeatherByCity("London")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("TAG", compositeDisposable.toString())
            }, {
            }))

    }
}