package com.example.goodweather.presentation.fragments

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.goodweather.R
import com.example.goodweather.data.repository.WeatherRepository
import com.example.goodweather.location.GPSLocation
import com.example.goodweather.location.GPSLocationInterface
import com.example.goodweather.presentation.viewmodel.WeatherTodayViewModel
import com.example.goodweather.presentation.viewmodel.factorys.ProvideFactoryWeatherToday
import kotlin.properties.Delegates

class WeatherTodayFragment : Fragment(), GPSLocationInterface {

    lateinit var viewModel : WeatherTodayViewModel
    lateinit var tvTemperature : TextView
    lateinit var ivWeather : AppCompatImageView
    private lateinit var locationManager: LocationManager
    private lateinit var gpsLocation: GPSLocation
    private var lat : Double? = null
    private var long : Double? = null
    private var location = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_today, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val provideFactoryWeatherToday = ProvideFactoryWeatherToday()
        viewModel = ViewModelProvider(this, provideFactoryWeatherToday).get(WeatherTodayViewModel::class.java)
        viewModel.fetchWeatherList(weatherRepository = WeatherRepository())

        locationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
        gpsLocation = GPSLocation()
        gpsLocation.setGPSLocationInterface(this)

        



//        viewModel.getWeatherByCity("London")
//
//        tvTemperature = view.findViewById(R.id.tvTemperature)
//        ivWeather = view.findViewById(R.id.ivWeather)
//
//        viewModel.weatherInformationLifeData.observe(viewLifecycleOwner, {
//            tvTemperature.text = it.main.temp.toString()
//            ivWeather.loadFromUrl(it.weather.icon)
//        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults[0] == RESULT_OK){
            checkPermissions()
        } else {
            Toast.makeText(requireContext(), getString(R.string.not_have_permission_GPS), Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermissions () {
        if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION), 1)
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60, 0f, gpsLocation)
        }
    }

    override fun getLocation(location: Location) {
        TODO("Not yet implemented")
    }
}