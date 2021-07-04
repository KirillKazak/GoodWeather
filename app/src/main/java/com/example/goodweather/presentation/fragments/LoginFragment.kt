package com.example.goodweather.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.goodweather.R
import com.example.goodweather.base.BaseActivity

class LoginFragment : Fragment() {

    lateinit var signUpButton : AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpButton = view.findViewById(R.id.sign_up_button)

        signUpButton.setOnClickListener {
            (requireActivity() as BaseActivity).fragmentRouter.openSignupFragment()
        }
    }
}