package com.example.goodweather.presentation.viewmodel.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodweather.data.remote.firebase.GoodWeatherFirebase
import com.example.goodweather.presentation.viewmodel.SignUpViewModel

class ProvideFactorySignUp(private val goodWeatherFirebase: GoodWeatherFirebase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpViewModel(goodWeatherFirebase) as T
    }
}