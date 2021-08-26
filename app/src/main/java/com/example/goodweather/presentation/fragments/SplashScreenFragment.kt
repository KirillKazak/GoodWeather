package com.example.goodweather.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.goodweather.R
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class SplashScreenFragment() : Fragment(), CoroutineScope {
    override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + Job()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveToLoginFragment()
    }

    private fun moveToLoginFragment () {
        launch {
            delay(2000)
            withContext(Dispatchers.Main) {
                requireView().findNavController()
                    .navigate(R.id.action_splashScreenFragment_to_loginFragment)
            }
        }
    }

}