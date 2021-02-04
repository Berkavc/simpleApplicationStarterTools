package com.example.b2CFinancialApp.ui.mainfragment

import android.os.Bundle
import android.view.View
import com.example.b2CFinancialApp.R
import com.example.b2CFinancialApp.databinding.FragmentMainBinding
import com.example.b2CFinancialApp.ui.BaseFragment
import com.example.b2CFinancialApp.utils.observe
import com.example.b2CFinancialApp.utils.viewBinding
import com.example.b2CFinancialApp.utils.viewModel

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
             //Navigation.findNavController(it).navigate(action)
            this.view?.let { fragmentView ->
                startFragmentWithNavigation(fragmentView, action)
            }
        }
    }

    private fun dummyFragmentTitle(dummyFragmentTitle: String?) {
        dummyFragmentTitle?.let {
            binding.textViewFragmentDummyTitle.text = it
        }
    }

}