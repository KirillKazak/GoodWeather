package com.example.goodweather.presentation.lifedata

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import androidx.lifecycle.LiveData
import com.example.goodweather.location.LocationDetails
import com.google.android.gms.location.*

class LocationLiveData(application: Application) : LiveData<LocationDetails>() {

    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    override fun onInactive() {
        super.onInactive()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        fusedLocationClient.lastLocation.addOnSuccessListener {
            location : Location -> location.also {
                setLocationData(it)
            }
        }
        startLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)

            locationResult ?: return
            for (location in locationResult.locations) {
                setLocationData(location)
            }
        }
    }

    private fun setLocationData(location : Location) {
        value = LocationDetails(location.latitude.toString(), location.longitude.toString())
    }

    companion object {
        val locationRequest : LocationRequest = LocationRequest.create().apply {
            interval = 6000
            fastestInterval = interval/4
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }
}
