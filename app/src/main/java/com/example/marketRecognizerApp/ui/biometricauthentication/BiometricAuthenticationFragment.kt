package com.example.marketRecognizerApp.ui.biometricauthentication

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.example.marketRecognizerApp.R
import com.example.marketRecognizerApp.databinding.FragmentBiometricAuthenticationBinding
import com.example.marketRecognizerApp.ui.BaseFragment
import com.example.marketRecognizerApp.utils.observe
import com.example.marketRecognizerApp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BiometricAuthenticationFragment : BaseFragment(R.layout.fragment_biometric_authentication) {
    private val biometricAuthenticationFragmentViewModel: BiometricAuthenticationFragmentViewModel by viewModels()
    private val binding by viewBinding(FragmentBiometricAuthenticationBinding::bind)
    private var controlEye = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(biometricAuthenticationFragmentViewModel) {
            observe(isWithPasswordClicked, ::controlWithPasswordClicked)
        }
        binding.lifecycleOwner = this
        binding.viewModel = biometricAuthenticationFragmentViewModel

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backPressedState()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )

        arrangeUI()

    }

    private fun arrangeUI() {
        binding.imageViewLogoMain.animate().setListener(object:Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
               if(controlEye){
                   reducedEye()
               }else{
                   expandEye()
               }
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationRepeat(animation: Animator?) {}

        })
        expandEye()
    }

    private fun expandEye() {
        controlEye = true
        binding.imageViewLogoMain
            .animate()
            .scaleX(1.5F)
            .scaleY(1.5F)
            .duration = 2000
    }

    private fun reducedEye() {
        controlEye = false
        binding.imageViewLogoMain
            .animate()
            .scaleX(0.5F)
            .scaleY(0.5F)
            .duration = 2000
    }

    private fun controlWithPasswordClicked(isClicked: Boolean?) {
        if (isClicked == true) {
            backPressedState()
        }
    }

    private fun backPressedState(){
        clearAnimation()
        customNavHostBackPress(this)
    }

    private fun clearAnimation(){
        binding.imageViewLogoMain.animate().setListener(null)
        binding.imageViewLogoMain.clearAnimation()
    }
}