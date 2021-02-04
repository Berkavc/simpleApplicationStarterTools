package com.example.b2CFinancialApp.ui.mainfragmentsecond

import android.os.Bundle
import android.view.View
import com.example.b2CFinancialApp.R
import com.example.b2CFinancialApp.databinding.FragmentMainSecondBinding
import com.example.b2CFinancialApp.ui.BaseFragment
import com.example.b2CFinancialApp.ui.mainfragment.MainFragmentArgs
import com.example.b2CFinancialApp.utils.observe
import com.example.b2CFinancialApp.utils.viewBinding
import com.example.b2CFinancialApp.utils.viewModel

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