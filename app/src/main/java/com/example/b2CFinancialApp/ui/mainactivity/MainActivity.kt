package com.example.b2CFinancialApp.ui.mainactivity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.b2CFinancialApp.R
import com.example.b2CFinancialApp.databinding.ActivityMainBinding
import com.example.b2CFinancialApp.models.DummyModels
import com.example.b2CFinancialApp.ui.BaseActivity
import com.example.b2CFinancialApp.utils.observe
import com.example.b2CFinancialApp.utils.viewModel

class MainActivity : BaseActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterRecyclerView: MainRecyclerViewAdapter
    private val arrayListNavigator: ArrayList<Int> = arrayListOf()

    //Drawer listener
    private lateinit var drawerListener: NavController.OnDestinationChangedListener
    private lateinit var leftAppBarConfiguration: AppBarConfiguration
    private lateinit var leftNavController : NavController
    private lateinit var drawerLayout: DrawerLayout

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

        //Left Drawer Setup
        leftNavController = findNavController(R.id.nav_host_fragment)
        drawerLayout = binding.drawerLayout

        binding.leftNav.setupWithNavController(leftNavController)
        leftAppBarConfiguration = AppBarConfiguration(leftNavController.graph,drawerLayout)
        setupActionBarWithNavController(leftNavController,leftAppBarConfiguration)

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
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(leftAppBarConfiguration) || super.onSupportNavigateUp()
        //return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.nav_host_fragment),null)
    }
}