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
                fragmentWeatherTodayBinding.tvLocation.text = "${data.name}, ${data.sys.country}"

                fragmentWeatherTodayBinding.tvTemperature.text =
                    data.main.temp.toInt().toString() + " \u2103"

                Glide.with(this)
                    .load("http://openweathermap.org/img/wn/"
                                + data.weather.get(0).icon + "@2x.png")
                    .into(fragmentWeatherTodayBinding.ivWeather)

                fragmentWeatherTodayBinding.tvDescription.text = data.weather.get(0).description
                fragmentWeatherTodayBinding.tvFeelsLike.text =
                    data.main.feelsLike.toInt().toString() + " \u2103"
                fragmentWeatherTodayBinding.tvTempMin.text =
                    data.main.tempMin.toInt().toString() + " \u2103"
                fragmentWeatherTodayBinding.tvTempMax.text =
                    data.main.tempMax.toInt().toString() + " \u2103"
                fragmentWeatherTodayBinding.tvPressure.text =
                    data.main.pressure.toString() + " " + getString(R.string.mbar)
                fragmentWeatherTodayBinding.tvHumidity.text =
                    data.main.humidity.toString() + " " + getString(R.string.per)
                fragmentWeatherTodayBinding.tvWindSpeed.text =
                    data.wind.speed.toString() + " " + getString(R.string.km_in_hour)
            }

        })
    }
}
