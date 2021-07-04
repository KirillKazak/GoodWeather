package com.example.goodweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goodweather.base.BaseActivity
import com.example.goodweather.base.FragmentRouter

class MainActivity: BaseActivity(R.id.container) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentRouter.openLoginFragment()
    }
}