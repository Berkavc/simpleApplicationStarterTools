package com.example.marketRecognizerApp.ui

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.marketRecognizerApp.R


//Base Fragment class inherit from this class in fragments rather than Fragment class and feel free to customize your activity methods.
abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {


    internal fun navigateToNextFragment(action: NavDirections) {
        view?.let { view ->
            viewModelStore.clear()
            Navigation.findNavController(view).navigate(action)
        }
    }

    internal fun navigateToNextActivity(intent: Intent) {
        activity?.finish()
        startActivity(intent)
    }

    internal fun navigateToWithoutPopNextActivity(intent: Intent) {
        startActivity(intent)
    }

    internal fun customNavHostBackPress(fragment: Fragment) {
        fragment.activity?.let {
            val controller = Navigation.findNavController(it, R.id.nav_host_fragment)
            controller?.let {navController ->
                navController.popBackStack()
            }
        }


    }

}