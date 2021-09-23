package com.example.goodweather.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
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

        viewModel.fetchWeatherList(fragmentWeatherInLocation.edtCity.text.toString())
    }

    fun getWeather() {
        viewModel.temperature.observe(viewLifecycleOwner, { data ->
            data?.let {
                fragmentWeatherInLocation.tvLocation.text = "${data.name}, ${data.sys.country}"

                fragmentWeatherInLocation.tvTemperature.text =
                    data.main.temp.toInt().toString() + "\u2103"

                Glide.with(this)
                    .load("http://openweathermap.org/img/wn/"
                            + data.weather.get(0).icon + "@2x.png")
                    .into(fragmentWeatherInLocation.ivWeather)

                fragmentWeatherInLocation.tvDescription.text = data.weather.get(0).description
                fragmentWeatherInLocation.tvFeelsLike.text = data.main.feelsLike.toInt().toString()
                fragmentWeatherInLocation.tvTempMin.text = data.main.tempMin.toInt().toString()
                fragmentWeatherInLocation.tvTempMax.text = data.main.tempMax.toInt().toString()
                fragmentWeatherInLocation.tvPressure.text = data.main.pressure.toString()
                fragmentWeatherInLocation.tvHumidity.text = data.main.humidity.toString()
                fragmentWeatherInLocation.tvWindSpeed.text = data.wind.speed.toString()
            }
        })
    }
}