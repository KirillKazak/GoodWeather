package com.example.goodweather.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity (private var container : Int) : AppCompatActivity() {

    val fragmentRouter: FragmentRouter = FragmentRouter(container, supportFragmentManager)
}