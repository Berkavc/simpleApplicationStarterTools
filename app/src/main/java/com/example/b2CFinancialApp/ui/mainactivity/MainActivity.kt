package com.example.b2CFinancialApp.ui.mainactivity

import android.os.Bundle
import com.example.b2CFinancialApp.databinding.ActivityMainBinding
import com.example.b2CFinancialApp.ui.BaseActivity
import com.example.b2CFinancialApp.utils.viewModel

class MainActivity : BaseActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mainActivityViewModel = viewModel(viewModelFactory) {

        }
    }
}