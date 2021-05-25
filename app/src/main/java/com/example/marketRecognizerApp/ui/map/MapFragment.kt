package com.example.marketRecognizerApp.ui.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marketRecognizerApp.R
import com.example.marketRecognizerApp.databinding.FragmentAnalysisBinding
import com.example.marketRecognizerApp.databinding.FragmentMapBinding
import com.example.marketRecognizerApp.ui.BaseFragment
import com.example.marketRecognizerApp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BaseFragment(R.layout.fragment_map) {
    private val mapFragmentViewModel: MapFragmentViewModel by viewModels()
    private val binding by viewBinding(FragmentMapBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(mapFragmentViewModel) {

        }
        binding.lifecycleOwner = this
        binding.viewModel = mapFragmentViewModel


        arrangeUI()

    }

    private fun arrangeUI() {

    }
}