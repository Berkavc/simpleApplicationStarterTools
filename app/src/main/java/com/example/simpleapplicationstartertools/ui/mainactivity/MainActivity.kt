package com.example.simpleapplicationstartertools.ui.mainactivity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleapplicationstartertools.R
import com.example.simpleapplicationstartertools.databinding.ActivityMainBinding
import com.example.simpleapplicationstartertools.models.DummyModels
import com.example.simpleapplicationstartertools.ui.BaseActivity
import com.example.simpleapplicationstartertools.utils.observe
import com.example.simpleapplicationstartertools.utils.viewModel

class MainActivity : BaseActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterRecyclerView: MainRecyclerViewAdapter
    private val arrayListNavigator: ArrayList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mainActivityViewModel = viewModel(viewModelFactory) {
            observe(dummyList, ::dummyListChanged)
            observe(controlDummyListVisibility, ::dummyListVisibility)
            observe(controlFragmentButtonClicked, ::dummyFragmentStarterButtonClicked)
        }
        binding.lifecycleOwner = this
        binding.viewModel = mainActivityViewModel
        mainActivityViewModel.gatherDummyList()
        arrangeUI()
    }

    private fun arrangeUI() {
        adapterRecyclerView = MainRecyclerViewAdapter(this, mutableListOf())
        with(binding.recyclerViewDummy) {
            adapterRecyclerView.onItemSelected = { position, item ->
                //Do something in recyclerView item click.
                Toast.makeText(this@MainActivity, "Item Clicked!!", Toast.LENGTH_SHORT)
                    .show()
            }
            adapter = adapterRecyclerView
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        //Bottom Navigation Bar Setup
        val bottomNavigationView = binding.bottomNav
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment,R.id.mainFragmentSecond))

        setupActionBarWithNavController(navController,appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)

    }


    private fun dummyListChanged(dummyList: MutableList<DummyModels.DummyListModel?>?) {
        dummyList?.let {
            // notify views.
            adapterRecyclerView.updateDataSource(it)
        }
    }

    private fun dummyListVisibility(controlDummyListVisibility: Boolean?) {
        if (controlDummyListVisibility == true) {
            // change visibilities of views.
            binding.textViewDummyTitle.visibility = View.VISIBLE
        }
    }

    private fun dummyFragmentStarterButtonClicked(isClicked: Boolean?) {
        if (isClicked == true) {
            // Start dummy fragment.
            val navController = Navigation.findNavController(this,R.id.nav_host_fragment)
            NavigationUI.setupActionBarWithNavController(this,navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.nav_host_fragment),null)
    }
}