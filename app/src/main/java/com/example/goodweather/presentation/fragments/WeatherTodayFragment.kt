package com.example.goodweather.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.goodweather.R
import com.example.goodweather.data.remote.firebase.GoodWeatherFirebase
import com.example.goodweather.data.remote.retrofit.ConfigureRetrofit
import com.example.goodweather.data.repository.WeatherRepository
import com.example.goodweather.presentation.viewmodel.LogInViewModel
import com.example.goodweather.presentation.viewmodel.WeatherTodayViewModel
import com.example.goodweather.presentation.viewmodel.factorys.ProvideFactoryLogIn
import com.example.goodweather.presentation.viewmodel.factorys.ProvideFactoryWeatherToday
import com.example.goodweather.utill.loadFromUrl

class WeatherTodayFragment : Fragment() {

    lateinit var viewModel : WeatherTodayViewModel
    lateinit var tvTemperature : TextView
    lateinit var ivWeather : AppCompatImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_today, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weatherRepository = WeatherRepository()
        val provideFactoryWeatherToday = ProvideFactoryWeatherToday(weatherRepository)
        viewModel = ViewModelProvider(this, provideFactoryWeatherToday).get(WeatherTodayViewModel::class.java)

        viewModel.getWeatherByCity("Minsk")

        tvTemperature = view.findViewById(R.id.tvTemperature)
        ivWeather = view.findViewById(R.id.ivWeather)

        viewModel.weatherInformationLifeData.observe(viewLifecycleOwner, {
            tvTemperature.text = it.main.temp.toString()
            ivWeather.loadFromUrl(it.weather.icon)
        })
    }
}