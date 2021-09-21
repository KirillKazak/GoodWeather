package com.example.goodweather.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.goodweather.R
import com.example.goodweather.databinding.FragmentWeatherTodayBinding
import com.example.goodweather.presentation.viewmodel.WeatherTodayViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class WeatherTodayFragment() : Fragment() {

    private lateinit var fragmentWeatherTodayBinding: FragmentWeatherTodayBinding
    private lateinit var viewModel: WeatherTodayViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentWeatherTodayBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_weather_today, container, false)
        return fragmentWeatherTodayBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentWeatherTodayBinding.weatherTodayFragment = this
        viewModel = ViewModelProviders.of(this).get(WeatherTodayViewModel::class.java)
        requestLocationUpdates()
        getTemperatureDetails()
    }

    private fun requestLocationUpdates() {
        viewModel.getLocationLifeData().observe(viewLifecycleOwner, {
            viewModel.fetchWeatherList(it.lat.toDouble(), it.long.toDouble()).toString()
        })
    }

    private fun getTemperatureDetails() {
        viewModel.temperature.observe(viewLifecycleOwner, { data ->
            data?.let {
                fragmentWeatherTodayBinding.tvTemperature.text = data.main.temp.toString()
                Glide.with(this)
                    .load("http://openweathermap.org/img/wn/" + data.weather.get(0).icon + "@2x.png")
                    .into(fragmentWeatherTodayBinding.ivWeather)
            }

        })
    }
}
