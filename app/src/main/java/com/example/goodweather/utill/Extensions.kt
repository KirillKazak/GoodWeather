package com.example.goodweather.utill

import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_weather_today.view.*

fun ImageView.loadFromUrl(icon: String?) {
    Glide.with(this)
        .load(icon)
        .into(ivWeather)
}