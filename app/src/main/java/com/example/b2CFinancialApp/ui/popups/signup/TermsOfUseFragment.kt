package com.example.b2CFinancialApp.ui.popups.signup

import android.os.Bundle
import android.view.View
import com.example.b2CFinancialApp.R
import com.example.b2CFinancialApp.databinding.DialogTermsOfUseBinding
import com.example.b2CFinancialApp.ui.BaseDialogFragment
import com.example.b2CFinancialApp.utils.observe
import com.example.b2CFinancialApp.utils.viewBinding
import com.example.b2CFinancialApp.utils.viewModel

class TermsOfUseFragment: BaseDialogFragment(R.layout.dialog_terms_of_use) {

    private lateinit var termOfUseViewModel: TermsOfUseViewModel
    private val binding by viewBinding(DialogTermsOfUseBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent().inject(this)
        termOfUseViewModel = viewModel(viewModelFactory) {

        }

        binding.lifecycleOwner = this
        binding.viewModel = termOfUseViewModel
        arrangeUI()
    }

    private fun arrangeUI() {

    }

}