package com.example.goodweather.base

import android.graphics.Insets.add
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.goodweather.presentation.fragments.LoginFragment
import com.example.goodweather.presentation.fragments.SignupFragment

class FragmentRouter (private val containerId : Int, private val fragmentManager: FragmentManager) : Router {

    private fun replaceFragment (fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }

    override fun openLoginFragment() {
        replaceFragment(LoginFragment())
    }

    override fun openSignupFragment() {
        replaceFragment(SignupFragment())
    }

}