package com.example.goodweather.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goodweather.data.remote.firebase.GoodWeatherFirebase

class LogInViewModel(private val goodWeatherFirebase: GoodWeatherFirebase) : ViewModel() {
    val errorStateLogIn: MutableLiveData<String> = MutableLiveData<String>()

    fun userSignIn(login: String, password: String) {
        goodWeatherFirebase.checkUserCredentialLogIn(login, password) { getMessage ->
            errorStateLogIn.value = getMessage
        }
    }
}