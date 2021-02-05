package com.example.b2CFinancialApp.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.b2CFinancialApp.R
import com.example.b2CFinancialApp.databinding.FragmentLoginBinding
import com.example.b2CFinancialApp.models.login.LoginState
import com.example.b2CFinancialApp.ui.BaseFragment
import com.example.b2CFinancialApp.ui.signup.SignUpFragmentArgs
import com.example.b2CFinancialApp.utils.hideKeyboard
import com.example.b2CFinancialApp.utils.observe
import com.example.b2CFinancialApp.utils.viewBinding
import com.example.b2CFinancialApp.utils.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import timber.log.Timber

class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private lateinit var loginViewModel: LoginFragmentViewModel
    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent().inject(this)
        loginViewModel = viewModel(viewModelFactory) {
            observe(isLoginClicked, ::controlLoginClicked)
            observe(isSignUpClicked, ::controlSignUpClicked)
            observe(isForgotPasswordClicked, ::controlForgotPasswordClicked)
            observe(isBioClicked, ::controlBioClicked)
            observe(isRefreshLoginPageState, ::controlRefreshPageState)
        }
        binding.lifecycleOwner = this
        binding.viewModel = loginViewModel
        arrangeUI()
    }

    private fun arrangeUI() {
        val coroutineCallGatherCheckBox = CoroutineScope(Dispatchers.IO)
        coroutineCallGatherCheckBox.async {
            loginViewModel.controlCheckBoxState()?.let { phoneNumber ->
                withContext(Dispatchers.Main) {
                    binding.checkBoxLogin.isChecked = true
                    binding.editTextPhoneNumber.setText(phoneNumber)
                }
            }
        }
        binding.checkBoxLogin.setOnCheckedChangeListener { buttonView, isChecked ->
            arrangeCheckBoxState()
        }
        val phoneNumber = SignUpFragmentArgs.fromBundle(requireArguments()).signUpPhoneNumber
        if (phoneNumber.isNotEmpty()) {
            binding.editTextPhoneNumber.setText(phoneNumber)
            binding.checkBoxLogin.isChecked = true
        }

    }

    private fun controlLoginClicked(isLoginClicked: Boolean?) {
        if (isLoginClicked == true) {
            //login states
            Timber.e("login_clicked!")
            context?.let { context ->
                view?.let { view ->
                    hideKeyboard(context, view)
                }
            }
            if (loginViewModel.controlCredentials(
                    userName = binding.editTextPhoneNumber.text.toString(),
                    password = binding.editTextPassword.text.toString()
                ) && loginViewModel.captchaResult.value == true
            ) {
                arrangeCheckBoxState()
                context?.let {
                    Toast.makeText(it, "Authentication Successful!", Toast.LENGTH_SHORT).show()
                }
            } else {
                var tryCounter = 0
                loginViewModel.loginTryCounter.value?.let {
                    tryCounter = it + 1
                    loginViewModel.loginTryCounter.postValue(tryCounter)
                }
                if (loginViewModel.captchaResult.value == false) {
                    context?.let {
                        Toast.makeText(it, "Verify that you're not robot!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                if (tryCounter in 3..4) {
                    loginViewModel.captchaResult.postValue(false)
                    loginViewModel.controlCaptcha()
                } else {
                    loginViewModel.captchaResult.postValue(true)
                }
                context?.let {
                    Toast.makeText(it, R.string.login_page_authentication_fail, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun controlSignUpClicked(isSignUpClicked: Boolean?) {
        if (isSignUpClicked == true) {
            //signUp states
            loginViewModel.controlSignUpState.value?.let {
                if (it == LoginState.CHECKED) {
                    loginViewModel.refreshCheckBoxStateOnRefreshState()

                } else {
                    viewModelStore.clear()
                    navigateToNextFragment(LoginFragmentDirections.actionFromLoginToSignUp())
                }


            }
            Timber.e("signUp_clicked!")
        }
    }

    private fun controlForgotPasswordClicked(isForgotPasswordClicked: Boolean?) {
        if (isForgotPasswordClicked == true) {
            //forgot password states
            Timber.e("forgot_password_clicked!")
        }
    }

    private fun controlBioClicked(isBioClicked: Boolean?) {
        if (isBioClicked == true) {
            //bio states
            Timber.e("bio_clicked!")
        }
    }

    private fun controlRefreshPageState(isRefreshPageState: Boolean?) {
        if (isRefreshPageState == true) {
            viewModelStore.clear()
            navigateToNextFragment(LoginFragmentDirections.actionFromLoginToLogin())
        }
    }

    private fun arrangeCheckBoxState() {
        if (binding.checkBoxLogin.isChecked) {
            loginViewModel.saveCheckBoxState(binding.editTextPhoneNumber.text.toString())
            loginViewModel.controlSignUpState.postValue(LoginState.CHECKED)
            binding.textViewSignUp.setText(R.string.login_page_sign_up_checked_title)
        } else {
            loginViewModel.refreshCheckBoxState()
            loginViewModel.controlSignUpState.postValue(LoginState.NOT_CHECKED)
            binding.textViewSignUp.setText(R.string.login_page_sign_up_title)
        }
    }


}