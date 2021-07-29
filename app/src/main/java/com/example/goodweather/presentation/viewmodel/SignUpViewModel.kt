package com.example.goodweather.presentation.viewmodel

import androidx.lifecycle.*
import com.example.goodweather.data.remote.firebase.GoodWeatherFirebase

class SignUpViewModel(private val goodWeatherFirebase: GoodWeatherFirebase) : ViewModel() {
    val errorStateSignUp : MutableLiveData<String> = MutableLiveData<String>()
    fun setErrorMessageInErrorState(login: String, password: String){
        goodWeatherFirebase.checkUserCredentialSignUp(login, password) { errorMessage ->
            errorStateSignUp.value = errorMessage
        }
    }
}