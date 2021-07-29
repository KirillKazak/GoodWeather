package com.example.goodweather.presentation.viewmodel.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodweather.data.remote.firebase.GoodWeatherFirebase
import com.example.goodweather.presentation.viewmodel.LogInViewModel

class ProvideFactoryLogIn (private val goodWeatherFirebase: GoodWeatherFirebase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LogInViewModel(goodWeatherFirebase) as T
    }
}