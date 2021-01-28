package com.example.simpleapplicationstartertools.ui.mainfragment

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.example.simpleapplicationstartertools.R
import com.example.simpleapplicationstartertools.databinding.FragmentMainBinding
import com.example.simpleapplicationstartertools.ui.BaseFragment
import com.example.simpleapplicationstartertools.utils.observe
import com.example.simpleapplicationstartertools.utils.viewBinding
import com.example.simpleapplicationstartertools.utils.viewModel

class MainFragment : BaseFragment(R.layout.fragment_main) {
    private lateinit var mainViewModel: MainFragmentViewModel
    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent().inject(this)
        mainViewModel = viewModel(viewModelFactory) {
            observe(mainTitle, ::dummyFragmentTitle)
        }
        arrangeUI()
    }

    private fun arrangeUI() {
        binding.button1.setOnClickListener {
            val action = MainFragmentDirections.actionFrom1To2()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun dummyFragmentTitle(dummyFragmentTitle: String?) {
        dummyFragmentTitle?.let {
            binding.textViewFragmentDummyTitle.text = it
        }
    }

}