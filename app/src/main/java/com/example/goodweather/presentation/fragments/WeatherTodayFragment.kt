package com.example.goodweather.presentation.fragments

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.goodweather.R
import com.example.goodweather.data.repository.WeatherRepository
import com.example.goodweather.databinding.FragmentWeatherTodayBinding
import com.example.goodweather.domain.entity.Main
import com.example.goodweather.domain.entity.WeatherResponse
import com.example.goodweather.presentation.viewmodel.WeatherTodayViewModel
import com.example.goodweather.presentation.viewmodel.factorys.ProvideFactoryWeatherToday
import com.example.goodweather.utill.Constants.Companion.PERMISSION_ID
import com.google.android.gms.location.*
import java.util.jar.Manifest

class WeatherTodayFragment : Fragment() {

    private lateinit var fragmentWeatherTodayBinding: FragmentWeatherTodayBinding
    private lateinit var viewModel : WeatherTodayViewModel
    private var main = Main(1,1,1,1,1)

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

        prepRequestLocationUpdates()
    }

    private fun prepRequestLocationUpdates() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            requestLocationUpdates()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_ID
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_ID -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocationUpdates()
                } else {
                    Toast.makeText(context, "Unable to update location without permission", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun requestLocationUpdates() {
        viewModel = ViewModelProviders.of(this).get(WeatherTodayViewModel::class.java)
        viewModel.getLocationLifeData().observe(viewLifecycleOwner, {
            viewModel.fetchWeatherList(it.lat.toDouble(), it.long.toDouble()).toString()
            fragmentWeatherTodayBinding.tvTemperature.text = main.temp.toString()
        })
    }
}
