package com.example.marketRecognizerApp.ui.mainactivity

import android.os.Bundle
import androidx.activity.viewModels
import com.example.marketRecognizerApp.databinding.ActivityMainBinding
import com.example.marketRecognizerApp.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        with(mainActivityViewModel) {

        }
    }

}