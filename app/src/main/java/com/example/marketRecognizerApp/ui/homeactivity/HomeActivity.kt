package com.example.marketRecognizerApp.ui.homeactivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.marketRecognizerApp.R
import com.example.marketRecognizerApp.databinding.ActivityHomeBinding
import com.example.marketRecognizerApp.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private val homeActivityViewModel: HomeActivityViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private var currentId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        with(homeActivityViewModel) {

        }

        val bottomNavigationView = binding.homeBottomNavBar
        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)

        currentId = bottomNavigationView.selectedItemId
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            if (currentId != item.itemId) {
                currentId = item.itemId
                navController.navigate(item.itemId)
            }
            true
        }
    }

}