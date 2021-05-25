package com.example.marketRecognizerApp.ui.detector

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marketRecognizerApp.R
import com.example.marketRecognizerApp.databinding.FragmentDetectorBinding
import com.example.marketRecognizerApp.ui.BaseFragment
import com.example.marketRecognizerApp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetectorFragment : BaseFragment(R.layout.fragment_detector) {
    private val detectorFragmentViewModel: DetectorFragmentViewModel by viewModels()
    private val binding by viewBinding(FragmentDetectorBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(detectorFragmentViewModel) {

        }
        binding.lifecycleOwner = this
        binding.viewModel = detectorFragmentViewModel


        arrangeUI()

    }

    private fun arrangeUI() {

    }
}