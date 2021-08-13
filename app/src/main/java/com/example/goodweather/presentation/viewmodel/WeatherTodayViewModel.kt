package com.example.goodweather.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.goodweather.data.remote.retrofit.WeatherAPI
import com.example.goodweather.data.repository.WeatherRepository
import com.example.goodweather.domain.entity.WeatherResponse
import com.example.goodweather.presentation.lifedata.LocationLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class WeatherTodayViewModel(context: Application) : AndroidViewModel(context)  {
    private val locationLifeDate = LocationLiveData(context)
    val weatherInformationLiveData : MutableLiveData<WeatherResponse> = MutableLiveData()
    private val weatherRepository = WeatherRepository()


    fun getLocationLifeData() = locationLifeDate

     fun fetchWeatherList(lat : Double, long : Double) = viewModelScope.launch {
         weatherRepository.getWeatherByLocationDetails(lat, long)
//        val response = weatherRepository.getWeatherByLocationDetails(lat, long)
//        val body = response.body()
//        weatherInformationLiveData.postValue(body)
    }

//    fun getWeatherByCity (cityName : String) = viewModelScope.launch {
//        val response = weatherRepository.getWeatherByCity(cityName)
//        val body = response?.body()
//        weatherInformationLifeData.postValue(body)
//        Log.d("TAG", weatherInformationLifeData.value?.name.toString())
//    }
}