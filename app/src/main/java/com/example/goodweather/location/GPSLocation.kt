package com.example.goodweather.location

import android.location.Location
import android.location.LocationListener

class GPSLocation : LocationListener {

    lateinit var gpsLocationInterface: GPSLocationInterface

    override fun onLocationChanged(location: Location) {
        gpsLocationInterface.getLocation(location)
    }

    fun setGPSLocationInterface (gpsLocationInterface: GPSLocationInterface) {
        this.gpsLocationInterface = gpsLocationInterface
    }

}