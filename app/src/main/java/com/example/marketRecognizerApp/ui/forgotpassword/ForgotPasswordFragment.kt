package com.example.marketRecognizerApp.ui.forgotpassword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marketRecognizerApp.R
import com.example.marketRecognizerApp.databinding.FragmentForgotPasswordBinding
import com.example.marketRecognizerApp.ui.BaseFragment
import com.example.marketRecognizerApp.utils.checkEmailFormat
import com.example.marketRecognizerApp.utils.initializeErrorDialogPopup
import com.example.marketRecognizerApp.utils.observe
import com.example.marketRecognizerApp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment(R.layout.fragment_forgot_password) {
    private val forgotPasswordFragmentViewModel: ForgotPasswordFragmentViewModel by viewModels()
    private val binding by viewBinding(FragmentForgotPasswordBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(forgotPasswordFragmentViewModel) {
            observe(isForgotPasswordClicked, ::controlContinueClicked)
        }
        binding.lifecycleOwner = this
        binding.viewModel = forgotPasswordFragmentViewModel
        arrangeUI()
    }

    private fun arrangeUI() {

        binding.editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.textViewForgotPasswordEmailError.visibility = View.GONE
            }

        })

    }

    private fun controlContinueClicked(isClicked: Boolean?) {
        if (isClicked == true) {
            var controlEmail = true

            when {
                binding.editTextEmail.text.isNullOrEmpty() -> {
                    controlEmail = false
                    binding.textViewForgotPasswordEmailError.visibility = View.VISIBLE
                    binding.textViewForgotPasswordEmailError.text =
                        resources.getString(R.string.error_sign_up_email_empty)
                }
                !checkEmailFormat(binding.editTextEmail.text.toString()) -> {
                    controlEmail = false
                    binding.textViewForgotPasswordEmailError.visibility = View.VISIBLE
                    binding.textViewForgotPasswordEmailError.text =
                        resources.getString(R.string.error_sign_up_email_invalid_format)
                }
                // Check Member Not Found From Cloud DB
                binding.editTextEmail.text.toString() != "berk@hotmail.com" -> {
                    controlEmail = false
                    activity?.let {
                        initializeErrorDialogPopup(
                            message = resources.getString(R.string.error_forgot_password_doesnt_exist),
                            context = it
                        )
                    }
                }
            }

            if (controlEmail) {
                navigateToNextFragment(ForgotPasswordFragmentDirections.actionFromForgotPasswordToVerification(
                    email = binding.editTextEmail.text.toString()
                ))
            }
        }
    }

}