package com.example.b2CFinancialApp.ui.mainfragmentsecond

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.b2CFinancialApp.R
import com.example.b2CFinancialApp.databinding.FragmentMainSecondBinding
import com.example.b2CFinancialApp.ui.BaseFragment
import com.example.b2CFinancialApp.utils.PasswordStrength
import com.example.b2CFinancialApp.utils.observe
import com.example.b2CFinancialApp.utils.viewBinding
import com.example.b2CFinancialApp.utils.viewModel
import kotlinx.android.synthetic.main.fragment_main_second.*

class MainFragmentSecond : BaseFragment(R.layout.fragment_main_second), TextWatcher {

    private lateinit var signUpViewModel: MainFragmentSecondViewModel
    private val binding by viewBinding(FragmentMainSecondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent().inject(this)
        signUpViewModel = viewModel(viewModelFactory) {
            observe(passwordCheck, ::checkPassword)
            observe(termsOfUseCheck, ::checkTermsOfUse)
        }
        arrangeUI()
    }

    private fun arrangeUI() {
        // Get argument from fragment
        // val args = MainFragmentArgs.fromBundle(requireArguments()).myArg
        // binding.textViewFragmentDummyTitle.text = args.toString()
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

        binding.buttonSignUp.setOnClickListener {
            if (signUpViewModel.passwordCheck.value == true && signUpViewModel.termsOfUseCheck.value == true
                && signUpViewModel.rememberMeCheck.value == true) {
                val action = MainFragmentSecondDirections.actionFrom2To1(password.text.toString())
                Navigation.findNavController(it).navigate(action)
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

    private fun checkPassword(checkPasswordControl: Boolean?) {
        if (checkPasswordControl == true) {

        }
    }

    private fun checkTermsOfUse(checkTermsOfUseControl: Boolean?) {
        if (checkTermsOfUseControl == true) {

        }
    }

    override fun afterTextChanged(s: Editable?) {
        updatePasswordStrengthView(s.toString())
        if (s != null) {
            signUpViewModel.passwordCheck.value = s.length >= 6 && progressBar.progress >= 50
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
            context?.let { str.getText(it) } == "Weak" -> {
                progressBar.progress = 25
            }
            context?.let { str.getText(it) } == "Medium" -> {
                progressBar.progress = 50
            }
            context?.let { str.getText(it) } == "Strong" -> {
                progressBar.progress = 75
            }
            else -> {
                progressBar.progress = 100
            }
        }
    }

}