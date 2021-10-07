package com.example.goodweather.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goodweather.data.repository.WeatherRepository
import com.example.goodweather.domain.entity.WeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherInLocationViewModel : ViewModel() {
    val temperature: MutableLiveData<WeatherResponse> = MutableLiveData()
    private val weatherRepository = WeatherRepository()
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun fetchWeatherList(cityName: String) {
        compositeDisposable.add(
            weatherRepository.getWeatherByCity(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    temperature.postValue(it)
                }, {

                })
        )
    }
}