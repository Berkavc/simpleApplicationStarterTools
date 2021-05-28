package com.example.marketRecognizerApp.ui.analysis

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marketRecognizerApp.R
import com.example.marketRecognizerApp.databinding.FragmentAnalysisBinding
import com.example.marketRecognizerApp.ui.BaseFragment
import com.example.marketRecognizerApp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalysisFragment : BaseFragment(R.layout.fragment_analysis) {
    private val analysisFragmentViewModel: AnalysisFragmentViewModel by viewModels()
    private val binding by viewBinding(FragmentAnalysisBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(analysisFragmentViewModel) {

        }
        binding.lifecycleOwner = this
        binding.viewModel = analysisFragmentViewModel


        arrangeUI()

    }

    private fun arrangeUI() {

    }
}