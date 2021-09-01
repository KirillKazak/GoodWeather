package com.example.goodweather.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.goodweather.data.repository.WeatherRepository
import com.example.goodweather.domain.entity.Main
import com.example.goodweather.domain.entity.WeatherResponse
import com.example.goodweather.presentation.lifedata.LocationLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherTodayViewModel(application: Application) : AndroidViewModel(application)  {
    val temperature : MutableLiveData<Main> = MutableLiveData()
    private val locationLifeDate = LocationLiveData(application)
    private val weatherRepository = WeatherRepository()
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getLocationLifeData() = locationLifeDate

    fun fetchWeatherList(lat : Double, long : Double) {
        compositeDisposable.add(weatherRepository.getWeatherByLocationDetails(lat, long)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {
            }))
    }
}