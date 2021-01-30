package com.example.simpleapplicationstartertools.ui.mainfragmentsecond

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.simpleapplicationstartertools.R
import com.example.simpleapplicationstartertools.databinding.FragmentMainSecondBinding
import com.example.simpleapplicationstartertools.ui.BaseFragment
import com.example.simpleapplicationstartertools.ui.mainfragment.MainFragmentArgs
import com.example.simpleapplicationstartertools.utils.observe
import com.example.simpleapplicationstartertools.utils.viewBinding
import com.example.simpleapplicationstartertools.utils.viewModel

class MainFragmentSecond : BaseFragment(R.layout.fragment_main_second) {
    private lateinit var mainViewModel: MainFragmentSecondViewModel
    private val binding by viewBinding(FragmentMainSecondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent().inject(this)
        mainViewModel = viewModel(viewModelFactory) {
            observe(mainTitle, ::dummyFragmentTitle)
        }
        arrangeUI()
    }

    private fun arrangeUI() {
        //get argument from fragment 1
        //val args = MainFragmentArgs.fromBundle(requireArguments()).myArg
        //binding.textViewFragmentDummyTitle.text = args.toString()
    }

    private fun dummyFragmentTitle(dummyFragmentTitle: String?) {
       // dummyFragmentTitle?.let {
        //   binding.textViewFragmentDummyTitle.text = it
       // }
    }

}