package com.example.goodweather.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.goodweather.R
import com.example.goodweather.data.remote.firebase.GoodWeatherFirebase
import com.example.goodweather.databinding.FragmentSignUpBinding
import com.example.goodweather.presentation.viewmodel.SignUpViewModel
import com.example.goodweather.presentation.viewmodel.factorys.ProvideFactorySignUp
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() { //TODO rename fragment +

    lateinit var viewModel: SignUpViewModel
    private lateinit var fragmentSignUpBinding : FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSignUpBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_sign_up, container, false)
        return fragmentSignUpBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val goodWeatherFirebase = GoodWeatherFirebase(requireContext())
        val provideFactorySignUp = ProvideFactorySignUp(goodWeatherFirebase)
        viewModel = ViewModelProvider(this, provideFactorySignUp).get(SignUpViewModel::class.java)

        fragmentSignUpBinding.signUpFragment = this

        viewModel.errorStateSignUp.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    fun signUp () {
        viewModel.setErrorMessageInErrorState(edtEmail.text.toString(),
            edtPassword.text.toString())
    }

    fun moveLoginFragment() {
        view?.findNavController()?.navigate(R.id.action_signUpFragment_to_loginFragment)
    }
}