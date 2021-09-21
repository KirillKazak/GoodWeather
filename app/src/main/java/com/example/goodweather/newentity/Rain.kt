package com.example.goodweather.newentity


import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    val h: Double
)