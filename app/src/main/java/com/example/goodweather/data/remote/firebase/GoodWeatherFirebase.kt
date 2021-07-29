package com.example.goodweather.data.remote.firebase

import android.content.Context
import android.text.TextUtils
import com.example.goodweather.R
import com.example.goodweather.utill.Constants.Companion.LENGTH_EIGHTEEN
import com.example.goodweather.utill.Constants.Companion.LENGTH_SIX
import com.google.firebase.auth.FirebaseAuth

class GoodWeatherFirebase (private val context: Context) {

    private val firebaseAuthentication : FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun checkUserCredentialSignUp(login: String, password : String, error: (String) -> Unit) {
        when {
            TextUtils.isEmpty(login) -> {
                error.invoke(context.getString(R.string.no_email))
            }
            TextUtils.isEmpty(password) -> {
                error.invoke(context.getString(R.string.no_password))
            }
            password.length < LENGTH_SIX -> {
                error.invoke(context.getString(R.string.short_password))
            }
            password.length > LENGTH_EIGHTEEN -> {
                error.invoke(context.getString(R.string.long_password))
            } else -> {
                getNewUser(login, password) {
                    error.invoke(it)
                }
            }
        }
    }

    fun checkUserCredentialLogIn (login: String, password: String, error: (String) -> Unit) {
        when {
            TextUtils.isEmpty(login) -> {
                error.invoke(context.getString(R.string.no_email))
            }
            TextUtils.isEmpty(password) -> {
                error.invoke(context.getString(R.string.no_password))
            } else -> {
                logIn(login, password) {
                    error.invoke(it)
                }
            }
        }
    }

    private fun getNewUser (login: String, password: String, getResult: (String) -> Unit = {} ) {
        firebaseAuthentication.createUserWithEmailAndPassword(login, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    getResult(context.getString(R.string.sign_up_success))
                } else {
                   getResult(context.getString(R.string.sign_up_error))
                }
            }
    }

    private fun logIn (login: String, password: String, getResult: (String) -> Unit = {}) {
        firebaseAuthentication.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener {
            if(it.isSuccessful) {
                getResult(context.getString(R.string.success))
            } else {
                getResult(context.getString(R.string.fail))
            }
        }
    }
}