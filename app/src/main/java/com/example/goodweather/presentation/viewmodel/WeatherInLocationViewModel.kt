package com.example.goodweather.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goodweather.data.repository.WeatherRepository
import com.example.goodweather.domain.entity.Main
import com.example.goodweather.presentation.lifedata.LocationLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherInLocationViewModel : ViewModel() {
    val temperature : MutableLiveData<Main> = MutableLiveData()
    private val weatherRepository = WeatherRepository()
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun fetchWeatherList(cityName : String) {
        compositeDisposable.add(weatherRepository.getWeatherByCity(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {
            })
        )
    }
}