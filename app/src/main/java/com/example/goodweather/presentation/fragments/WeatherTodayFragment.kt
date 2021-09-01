package com.example.goodweather.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.goodweather.R
import com.example.goodweather.databinding.FragmentWeatherTodayBinding
import com.example.goodweather.presentation.viewmodel.WeatherTodayViewModel

class WeatherTodayFragment : Fragment() {

    private lateinit var fragmentWeatherTodayBinding: FragmentWeatherTodayBinding
    private lateinit var viewModel : WeatherTodayViewModel

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

        requestLocationUpdates()
    }

    private fun requestLocationUpdates() {
        viewModel = ViewModelProviders.of(this).get(WeatherTodayViewModel::class.java)
        viewModel.getLocationLifeData().observe(viewLifecycleOwner, {
            viewModel.fetchWeatherList(it.lat.toDouble(), it.long.toDouble()).toString()
            fragmentWeatherTodayBinding.tvTemperature.text = viewModel.temperature.value?.temp.toString()
        })
    }
}
