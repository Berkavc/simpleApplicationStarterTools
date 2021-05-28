package com.example.marketRecognizerApp.ui.errordialog

import android.os.Bundle
import androidx.activity.viewModels
import com.example.marketRecognizerApp.R
import com.example.marketRecognizerApp.databinding.ActivityErrorDialogBinding
import com.example.marketRecognizerApp.ui.BaseActivity
import com.example.marketRecognizerApp.utils.observe

class ErrorDialogActivity : BaseActivity() {
    private val errorDialogActivityViewModel: ErrorDialogViewModel by viewModels()
    private lateinit var binding: ActivityErrorDialogBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Dialog)
        super.onCreate(savedInstanceState)
        binding = ActivityErrorDialogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setFinishOnTouchOutside(false)
        with(errorDialogActivityViewModel) {
            observe(genericButtonClicked, ::onGenericButtonClicked)
        }
        binding.lifecycleOwner = this
        binding.viewModel = errorDialogActivityViewModel
        arrangeUI()
    }

    private fun arrangeUI() {
        binding.textViewErrorDialogTitle.text = intent.extras?.getString("message") ?: ""
        binding.buttonErrorDialog.text = intent.extras?.getString("buttonMessage")
            ?: resources.getString(R.string.error_dialog_ok_message)
    }

    private fun onGenericButtonClicked(clicked: Boolean?) {
        if (clicked == true) {
            finish()
        }
    }
}