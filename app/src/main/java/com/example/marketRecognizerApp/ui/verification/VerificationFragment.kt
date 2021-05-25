package com.example.marketRecognizerApp.ui.verification

import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.Spanned
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.marketRecognizerApp.R
import com.example.marketRecognizerApp.databinding.FragmentVerificationBinding
import com.example.marketRecognizerApp.ui.BaseFragment
import com.example.marketRecognizerApp.utils.observe
import com.example.marketRecognizerApp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.*

@AndroidEntryPoint
class VerificationFragment : BaseFragment(R.layout.fragment_verification) {
    private val verificationFragmentViewModel: VerificationFragmentViewModel by viewModels()
    private val binding by viewBinding(FragmentVerificationBinding::bind)
    private var countDownTimer: Timer? = Timer()
    private var secondsRemaining: Long = 10
    private var timerUpState = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(verificationFragmentViewModel) {
            observe(isVerifyClicked, ::controlVerifyClicked)
        }
        binding.lifecycleOwner = this
        binding.viewModel = verificationFragmentViewModel

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                timerUpState()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )

        gatherArgs()
        arrangeUI()
        countdownTimer(
            resources.getString(R.string.verification_description_timer),
            resources.getString(R.string.verification_description_timer_up_state)
        )
    }

    private fun gatherArgs(){
        verificationFragmentViewModel.email.value = VerificationFragmentArgs.fromBundle(requireArguments()).email
    }

    private fun arrangeUI() {
        binding.editTextVerificationCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                context?.let {
                    if (binding.editTextVerificationCode.length() > 0) {
                        binding.buttonVerification.isClickable = true
                        binding.buttonVerification.background =
                            ContextCompat.getDrawable(it, R.drawable.bg_black_label)
                    } else {
                        binding.buttonVerification.isClickable = false
                        binding.buttonVerification.background =
                            ContextCompat.getDrawable(it, R.drawable.bg_grey_label)
                    }
                }
                binding.textViewVerificationCodeError.visibility = View.GONE
            }

        })

    }

    private fun controlVerifyClicked(isClicked: Boolean?) {
        if (isClicked == true) {
            var controlVerificationCode = true

            // Check Invalid Verification Code From DB
            if (binding.editTextVerificationCode.text.toString() != "1234") {
                controlVerificationCode = false
                binding.textViewVerificationCodeError.visibility = View.VISIBLE
                binding.textViewVerificationCodeError.text =
                    resources.getString(R.string.error_verification_invalid_code)
            }

            if (controlVerificationCode) {
                navigateToNextFragment(VerificationFragmentDirections.actionFromVerificationToLogin())
            }

        }
    }


    private fun countdownTimer(timerMessage: String, timerUpMessage: String) {
        val coroutineScopeCountDown = CoroutineScope(Dispatchers.Main)
        val countDownTimerTask = object : TimerTask() {
            override fun run() {
                val spannable: Spanned?
                secondsRemaining -= 1
                if (secondsRemaining != 0L) {
                    spannable =
                        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                            Html.fromHtml(
                                timerMessage.format(
                                    secondsRemaining,
                                    verificationFragmentViewModel.email.value

                                )
                            )
                        } else {
                            Html.fromHtml(
                                timerMessage.format(
                                    secondsRemaining,
                                    verificationFragmentViewModel.email.value
                                ), Html.FROM_HTML_MODE_LEGACY
                            )

                        }
                } else {
                    countDownTimer?.cancel()
                    spannable =
                        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                            Html.fromHtml(timerUpMessage)
                        } else {
                            Html.fromHtml(timerUpMessage, Html.FROM_HTML_MODE_LEGACY)

                        }
                    coroutineScopeCountDown.launch {
                        delay(1000)
                        timerUpState()
                    }
                }
                coroutineScopeCountDown.launch {
                    withContext(Dispatchers.Main) {
                        if (!this@VerificationFragment.isDetached) {
                            binding.textViewVerificationCountDown.setText(
                                spannable,
                                TextView.BufferType.SPANNABLE
                            )
                        }
                    }
                }
            }
        }
        countDownTimer?.schedule(countDownTimerTask, 0, 1000)
    }

    private fun timerUpState() {
        cleanFragment()
        navigateTimerUpState()
    }

    private fun cleanFragment() {
        countDownTimer?.cancel()
        countDownTimer = null
        timerUpState = true
    }

    private fun navigateTimerUpState() {
        customNavHostBackPress(this)
    }

    override fun onResume() {
        super.onResume()
        if (timerUpState) {
            timerUpState()
        }
    }
}