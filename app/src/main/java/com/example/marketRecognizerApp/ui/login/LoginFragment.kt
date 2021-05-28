package com.example.marketRecognizerApp.ui.login

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.example.marketRecognizerApp.R
import com.example.marketRecognizerApp.databinding.FragmentLoginBinding
import com.example.marketRecognizerApp.models.ApiModels
import com.example.marketRecognizerApp.models.RoomModels
import com.example.marketRecognizerApp.ui.BaseFragment
import com.example.marketRecognizerApp.ui.homeactivity.HomeActivity
import com.example.marketRecognizerApp.utils.initializeErrorDialogPopup
import com.example.marketRecognizerApp.utils.observe
import com.example.marketRecognizerApp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val loginFragmentViewModel: LoginFragmentViewModel by viewModels()
    private val binding by viewBinding(FragmentLoginBinding::bind)
    private var controlEye: LoginEyeAnimationMovement = LoginEyeAnimationMovement.CENTER
    private var coroutineEyeAnimation = CoroutineScope(Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(loginFragmentViewModel) {
            observe(isLoginClicked, ::controlLoginClicked)
            observe(isSignUpClicked, ::controlSignUpClicked)
            observe(isForgotPasswordClicked, ::controlForgotPasswordClicked)
            observe(isBioClicked, ::controlBioClicked)
            observe(loginResponseModel, ::controlAuthentication)
            observe(localLoginResponseModel, ::controlLocalUser)
        }
        binding.lifecycleOwner = this
        binding.viewModel = loginFragmentViewModel
        arrangeUI()

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

    private fun arrangeUI() {
        loginFragmentViewModel.gatherCheckBoxState()
        //Check Biometric State and change button according to biometric support.
        checkBiometric()

        arrangeEyeAnimation()
    }

    private fun controlLocalUser(loginModel: RoomModels.LoginModel?) {
        loginModel?.let {
            if (it.rememberMe) {
                binding.checkBoxLogin.isChecked = true
                binding.editTextUserName.setText(it.userName ?: "")
            }
        }
        binding.checkBoxLogin.setOnCheckedChangeListener { buttonView, isChecked ->
            saveCheckBox(isChecked)
        }
    }

    private fun controlLoginClicked(isClicked: Boolean?) {
        if (isClicked == true) {
            if (!binding.editTextUserName.text.isNullOrEmpty() && !binding.editTextPassword.text.isNullOrEmpty()) {
//                loginFragmentViewModel.controlCredentials(
//                    binding.editTextUserName.text.toString(), binding.checkBoxLogin.isChecked
//                )
                saveCheckBox(binding.checkBoxLogin.isChecked)
                clearAnimation()
                context?.let {
                    val intent = Intent(it, HomeActivity::class.java)
                    navigateToNextActivity(intent)
                }

            } else {
                activity?.let {
                    initializeErrorDialogPopup(
                        message = resources.getString(R.string.error_login_page_empty_credentials),
                        buttonMessage = null,
                        context = it
                    )
                }
            }
        }
    }

    private fun controlSignUpClicked(isClicked: Boolean?) {
        if (isClicked == true) {
            clearAnimation()
            navigateToNextFragment(LoginFragmentDirections.actionFromLoginToSignUp())
        }
    }

    private fun controlForgotPasswordClicked(isClicked: Boolean?) {
        if (isClicked == true) {
            clearAnimation()
            navigateToNextFragment(LoginFragmentDirections.actionFromLoginToForgotPassword())
        }
    }

    private fun controlBioClicked(isClicked: Boolean?) {
        if (isClicked == true) {
            clearAnimation()
            navigateToNextFragment(LoginFragmentDirections.actionFromLoginToBiometricAuthentication())
        }
    }

    private fun controlAuthentication(loginModel: ApiModels.LoginModel?) {
        loginModel?.let {
            loginFragmentViewModel.saveCheckBoxState(
                userName = it.userName,
                rememberMe = it.rememberMe
            )
        }
    }

    private fun saveCheckBox(isChecked: Boolean) {
        if (isChecked) {
            loginFragmentViewModel.saveCheckBoxState(
                userName = binding.editTextUserName.text.toString(),
                rememberMe = true
            )
        } else {
            loginFragmentViewModel.saveCheckBoxState(
                userName = null,
                rememberMe = false
            )
        }
    }

    private fun checkBiometric() {
        /*    context?.let {
                val biometricManager = BiometricManager.from(it)
                when (biometricManager.canAuthenticate(BIOMETRIC_WEAK)) {
                    BiometricManager.BIOMETRIC_SUCCESS -> {
                        loginViewModel.biometricSupported.value = true
                    }
                    BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                        loginViewModel.biometricSupported.value = true
                    }
                    else -> {
                        binding.textViewLoginWithBio.setTextColor(ContextCompat.getColor(it, R.color.figma_ghosted))
                        loginViewModel.biometricSupported.value = false
                    }
                }
            }*/
    }

    private fun arrangeEyeAnimation() {
        binding.imageViewLogoMain.invalidate()
        binding.imageViewLogoMain.animate().setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                coroutineEyeAnimation = CoroutineScope(Dispatchers.Main)
                coroutineEyeAnimation.launch {
                    when (controlEye) {
                        LoginEyeAnimationMovement.RIGHT_UP -> moveEyeLeft()
                        LoginEyeAnimationMovement.LEFT_UP -> moveEyeCenter()
                        LoginEyeAnimationMovement.CENTER -> {
                            delay(750)
                            moveEyeRight()
                        }
                    }
                }
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationRepeat(animation: Animator?) {}

        })
        moveEyeRight()
    }

    private fun moveEyeRight() {
        controlEye = LoginEyeAnimationMovement.RIGHT_UP
        binding.imageViewLogoMain
            .animate()
            .translationY(75F)
            .translationX(150F)
            .duration = 500
    }

    private fun moveEyeLeft() {
        controlEye = LoginEyeAnimationMovement.LEFT_UP
        binding.imageViewLogoMain
            .animate()
            .translationX(-150F)
            .duration = 500
    }

    private fun moveEyeCenter() {
        controlEye = LoginEyeAnimationMovement.CENTER
        binding.imageViewLogoMain
            .animate()
            .translationX(0F)
            .translationY(0F)
            .duration = 500
    }

    private fun clearAnimation() {
        if (coroutineEyeAnimation.isActive) {
            coroutineEyeAnimation.cancel()
        }
        binding.imageViewLogoMain.animate().setListener(null)
        binding.imageViewLogoMain.clearAnimation()
    }

}