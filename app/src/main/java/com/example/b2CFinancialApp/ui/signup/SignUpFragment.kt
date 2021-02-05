package com.example.b2CFinancialApp.ui.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.b2CFinancialApp.R
import com.example.b2CFinancialApp.databinding.FragmentSignUpBinding
import com.example.b2CFinancialApp.models.signup.SignUpState
import com.example.b2CFinancialApp.ui.BaseFragment
import com.example.b2CFinancialApp.ui.login.LoginFragmentDirections
import com.example.b2CFinancialApp.utils.PasswordStrength
import com.example.b2CFinancialApp.utils.observe
import com.example.b2CFinancialApp.utils.viewBinding
import com.example.b2CFinancialApp.utils.viewModel

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up), TextWatcher {

    private lateinit var signUpViewModel: SignUpViewModel
    private val binding by viewBinding(FragmentSignUpBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent().inject(this)
        signUpViewModel = viewModel(viewModelFactory) {
            observe(termsOfUseClicked, ::onTermsOfUseClicked)
            observe(signUpClicked, ::onSignUpClicked)
            observe(marketingPermissionClicked, ::onMarketingPermissionClicked)
        }
        binding.lifecycleOwner = this
        binding.viewModel = signUpViewModel
        arrangeUI()
    }

    private fun onSignUpClicked(signUpClicked: Boolean?) {
        if(signUpClicked == true) {
            if (signUpViewModel.passwordCheck.value == true && binding.checkboxTermsOfUse.isChecked) {
                if (binding.checkboxRememberMe.isChecked) {
                    navigateToNextFragment(SignUpFragmentDirections.actionFromSignUpToLogin(
                            binding.editTextPhoneNumber.text.toString()))
                } else {
                    navigateToNextFragment(SignUpFragmentDirections.actionFromSignUpToLogin())
                }
            } else {
                if (signUpViewModel.passwordCheck.value == false) {
                    Toast.makeText(context, "Your password is too weak to continue!", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(context, "Please read and check Terms of Use", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onTermsOfUseClicked(termsOfUseClicked: Boolean?) {
        if (termsOfUseClicked == true){
            navigateToNextFragment(SignUpFragmentDirections.actionSignUpFragmentToTermsOfUseFragment())
        }
    }

    private fun onMarketingPermissionClicked(marketingPermissionClicked: Boolean?) {
        if (marketingPermissionClicked == true) {
            navigateToNextFragment(SignUpFragmentDirections.actionSignUpFragmentToTermsOfUseFragment())
        }
    }

    private fun arrangeUI() {
        val password = binding.editTextPassword
        val termsOfUsageCheckbox = binding.checkboxTermsOfUse
        val rememberMeCheckbox = binding.checkboxRememberMe

        password.addTextChangedListener(this)

        termsOfUsageCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                signUpViewModel.termsOfUseCheck.value = true
            }
        }

        rememberMeCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                signUpViewModel.rememberMeCheck.value = true
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        updatePasswordStrengthView(s.toString())
        if (s != null) {
            signUpViewModel.passwordCheck.value = s.length >= 6 && binding.progressBar.progress >= 50
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    private fun updatePasswordStrengthView(password: String) {

        val progressBar = binding.progressBar
        val strengthView = binding.passwordStrength
        if (TextView.VISIBLE != strengthView.visibility)
            return

        if (TextUtils.isEmpty(password)) {
            strengthView.text = ""
            progressBar.progress = 0
            return
        }

        val str = PasswordStrength.calculateStrength(password)
        strengthView.text = context?.let { str.getText(it) }
        strengthView.setTextColor(str.color)

        progressBar.progressDrawable.setColorFilter(str.color, android.graphics.PorterDuff.Mode.SRC_IN)
        when {
            context?.let { str.getText(it) } == SignUpState.WEAK.value -> {
                progressBar.progress = 25
            }
            context?.let { str.getText(it) } == SignUpState.MEDIUM.value -> {
                progressBar.progress = 50
            }
            context?.let { str.getText(it) } == SignUpState.STRONG.value -> {
                progressBar.progress = 75
            }
            else -> {
                progressBar.progress = 100
            }
        }
    }

    private fun navigateToNextFragment(action: NavDirections) {
        view?.let { view ->
            Navigation.findNavController(view).navigate(action)
        }
    }

}