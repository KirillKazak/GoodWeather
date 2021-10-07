package com.example.goodweather.presentation.fragments

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
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
import com.example.goodweather.presentation.viewmodel.WeatherTodayViewModel

class WeatherInLocationFragment : Fragment() {

    private lateinit var fragmentWeatherInLocation: FragmentWeatherInLocationBinding
    private lateinit var viewModel: WeatherInLocationViewModel
    private lateinit var cityName: String
    private var sharedPreferences: SharedPreferences? = null
    private var KEY: String = "CITY_NAME"
    private var KEY_WORD: String? = "London"

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
    }

    fun getWeather() {
        sharedPreferences = requireContext().getSharedPreferences(KEY, MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putString(KEY_WORD, fragmentWeatherInLocation.edtCity.text.toString())
        editor?.apply()

        viewModel.fetchWeatherList(sharedPreferences?.getString(KEY_WORD, "error").toString())

        viewModel.temperature.observe(viewLifecycleOwner, { data ->
            data?.let {
                fragmentWeatherInLocation.tvLocation.text = "${data.name}, ${data.sys.country}"

                fragmentWeatherInLocation.tvTemperature.text =
                    data.main.temp.toInt().toString() + " \u2103"

                Glide.with(this).load("http://openweathermap.org/img/wn/"
                            + data.weather.get(0).icon + "@2x.png")
                    .into(fragmentWeatherInLocation.ivWeather)

                fragmentWeatherInLocation.tvDescription.text = data.weather.get(0).description
                fragmentWeatherInLocation.tvFeelsLike.text =
                    data.main.feelsLike.toInt().toString() + " \u2103"
                fragmentWeatherInLocation.tvTempMin.text =
                    data.main.tempMin.toInt().toString() + " \u2103"
                fragmentWeatherInLocation.tvTempMax.text =
                    data.main.tempMax.toInt().toString() + " \u2103"
                fragmentWeatherInLocation.tvPressure.text =
                    data.main.pressure.toString() + " " + getString(R.string.mbar)
                fragmentWeatherInLocation.tvHumidity.text =
                    data.main.humidity.toString() + " " + getString(R.string.per)
                fragmentWeatherInLocation.tvWindSpeed.text =
                    data.wind.speed.toString() + " " + getString(R.string.km_in_hour)
            }
        })
    }
}