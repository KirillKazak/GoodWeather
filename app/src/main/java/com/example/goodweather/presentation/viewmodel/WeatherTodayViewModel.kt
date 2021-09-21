package com.example.goodweather.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.goodweather.data.repository.WeatherRepository
import com.example.goodweather.domain.entity.Main
import com.example.goodweather.domain.entity.WeatherResponse
import com.example.goodweather.newentity.NewWeatherResponse
import com.example.goodweather.presentation.lifedata.LocationLiveData
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response

class WeatherTodayViewModel(application: Application) : AndroidViewModel(application) {
    var temperature: MutableLiveData<NewWeatherResponse> = MutableLiveData()
    private val locationLifeDate = LocationLiveData(application)
    private val weatherRepository = WeatherRepository()
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getLocationLifeData(): LocationLiveData = locationLifeDate

    fun fetchWeatherList(lat: Double, long: Double) {
        compositeDisposable.add(
            weatherRepository.getWeatherByLocationDetails(lat, long)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    temperature.postValue(it)
                }, {

                })
        )
    }
}