package com.example.simpleapplicationstartertools.ui

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.simpleapplicationstartertools.di.components.DaggerFragmentComponent
import com.example.simpleapplicationstartertools.di.components.FragmentComponent
import com.example.simpleapplicationstartertools.di.modules.FragmentModule
import javax.inject.Inject

//Base Fragment class inherit from this class in fragments rather than Fragment class and feel free to customize your activity methods.
abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var mFragmentComponent: FragmentComponent? = null
    protected fun fragmentComponent(): FragmentComponent {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule(this))
                .activityComponent((activity as BaseActivity).activityComponent())
                .build()
        }

        return mFragmentComponent as FragmentComponent
    }

    internal fun startFragmentWithNavigation(view: View, navDirections: NavDirections) {
        Navigation.findNavController(view).navigate(navDirections)
    }
}