package com.example.goodweather.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.goodweather.R
import com.example.goodweather.databinding.FragmentWeatherInLocationBinding
import com.example.goodweather.presentation.viewmodel.WeatherInLocationViewModel

class WeatherInLocationFragment : Fragment() {

    private lateinit var fragmentWeatherInLocation: FragmentWeatherInLocationBinding
    private lateinit var viewModel: WeatherInLocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentWeatherInLocation = DataBindingUtil
            .inflate(inflater, R.layout.fragment_weather_in_location, container, false)
        return fragmentWeatherInLocation.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentWeatherInLocation.weatherInLocationFragment = this

        viewModel = ViewModelProviders.of(this).get(WeatherInLocationViewModel::class.java)

        viewModel.fetchWeatherList("Minsk")
    }

    fun getWeather() {
        viewModel.temperature.observe(viewLifecycleOwner, { data ->
            data?.let {
                fragmentWeatherInLocation.tvTemperature.text = it.main.temp.toString()
            }
        })
    }
}