package com.example.goodweather.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.goodweather.R
import com.example.goodweather.data.remote.firebase.GoodWeatherFirebase
import com.example.goodweather.databinding.FragmentLogInBinding
import com.example.goodweather.presentation.viewmodel.LogInViewModel
import com.example.goodweather.presentation.viewmodel.factorys.ProvideFactoryLogIn

class LogInFragment : Fragment() {

    lateinit var viewModel: LogInViewModel
    private lateinit var fragmentLogInBinding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentLogInBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_log_in, container, false)
        return fragmentLogInBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goodWeatherFirebase = GoodWeatherFirebase(requireContext())
        val provideFactoryLogIn = ProvideFactoryLogIn(goodWeatherFirebase)
        viewModel = ViewModelProvider(this, provideFactoryLogIn).get(LogInViewModel::class.java)

        fragmentLogInBinding.logInFragment = this

        viewModel.errorStateLogIn.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    fun logIn() {
        viewModel.userSignIn(fragmentLogInBinding.edtEmail.text.toString(),
            fragmentLogInBinding.edtPassword.text.toString())

        viewModel.errorStateLogIn.observe(viewLifecycleOwner, {
           if (it == context?.getString(R.string.success)) {
               moveToWeatherTodayFragment()
           }
        })
    }

    fun moveToSignUpFragment() {
        requireView().findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
    }

    private fun moveToWeatherTodayFragment() {
        requireView().findNavController().navigate(R.id.action_loginFragment_to_weatherTodayFragment)
    }
}