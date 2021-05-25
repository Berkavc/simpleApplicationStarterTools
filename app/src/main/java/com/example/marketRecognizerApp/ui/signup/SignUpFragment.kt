package com.example.marketRecognizerApp.ui.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marketRecognizerApp.R
import com.example.marketRecognizerApp.databinding.FragmentSignUpBinding
import com.example.marketRecognizerApp.ui.BaseFragment
import com.example.marketRecognizerApp.utils.checkEmailFormat
import com.example.marketRecognizerApp.utils.initializeErrorDialogPopup
import com.example.marketRecognizerApp.utils.observe
import com.example.marketRecognizerApp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {
    private val signUpFragmentViewModel: SignUpFragmentViewModel by viewModels()
    private val binding by viewBinding(FragmentSignUpBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(signUpFragmentViewModel) {
            observe(isSignUpClicked, ::controlSignUpClicked)
        }
        binding.lifecycleOwner = this
        binding.viewModel = signUpFragmentViewModel
        arrangeUI()
    }

    private fun arrangeUI() {

        binding.editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.textViewSignUpEmailError.visibility = View.GONE
            }

        })

        binding.editTextUserName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.textViewSignUpUserNameError.visibility = View.GONE
            }

        })

        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.textViewSignUpUserPasswordError.visibility = View.GONE
            }

        })

        binding.editTextPasswordRetry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.textViewSignUpUserPasswordRetryError.visibility = View.GONE
            }

        })

    }

    private fun controlSignUpClicked(isClicked: Boolean?) {
        if (isClicked == true) {

            var controlEmail = true
            var controlUserName = true
            var controlPassword = true
            var controlPasswordRetry = true

            when {
                binding.editTextEmail.text.isNullOrEmpty() -> {
                    controlEmail = false
                    binding.textViewSignUpEmailError.visibility = View.VISIBLE
                    binding.textViewSignUpEmailError.text =
                        resources.getString(R.string.error_sign_up_email_empty)
                }
                !checkEmailFormat(binding.editTextEmail.text.toString()) -> {
                    controlEmail = false
                    binding.textViewSignUpEmailError.visibility = View.VISIBLE
                    binding.textViewSignUpEmailError.text =
                        resources.getString(R.string.error_sign_up_email_invalid_format)
                }
                // Check Already Member From Cloud DB
                binding.editTextEmail.text.toString() == "berk@hotmail.com" -> {
                    activity?.let {
                        initializeErrorDialogPopup(
                            message = resources.getString(R.string.error_sign_up_email_already_exist),
                            context = it
                        )
                    }
                }
            }

            if (binding.editTextUserName.text.isNullOrEmpty()) {
                controlUserName = false
                binding.textViewSignUpUserNameError.visibility = View.VISIBLE
                binding.textViewSignUpUserNameError.text =
                    resources.getString(R.string.error_sign_up_user_name_empty)
            }
            //Check If User Name Is Used From Cloud DB
            else if (binding.editTextUserName.text.toString() == "Berk") {
                activity?.let {
                    initializeErrorDialogPopup(
                        message = resources.getString(R.string.error_sign_up_user_name_exist),
                        context = it
                    )
                }
            }

            if (binding.editTextPassword.text.isNullOrEmpty()) {
                controlPassword = false
                binding.textViewSignUpUserPasswordError.visibility = View.VISIBLE
                binding.textViewSignUpUserPasswordError.text =
                    resources.getString(R.string.error_sign_up_password_empty)
            }

            if (binding.editTextPasswordRetry.text.isNullOrEmpty()) {
                controlPasswordRetry = false
                binding.textViewSignUpUserPasswordRetryError.visibility = View.VISIBLE
                binding.textViewSignUpUserPasswordRetryError.text =
                    resources.getString(R.string.error_sign_up_password_re_enter_empty)
            }

            if (binding.editTextPassword.text.toString() != binding.editTextPasswordRetry.text.toString()) {
                controlPassword = false
                controlPasswordRetry = false
                activity?.let {
                    initializeErrorDialogPopup(
                        message = resources.getString(R.string.error_sign_up_password_not_match),
                        context = it
                    )
                }
            }

            if (controlEmail && controlUserName && controlPassword && controlPasswordRetry) {
                navigateToNextFragment(SignUpFragmentDirections.actionFromSignUpToLogin())
            }


        }
    }

}