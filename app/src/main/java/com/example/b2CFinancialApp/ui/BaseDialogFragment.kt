package com.example.b2CFinancialApp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.b2CFinancialApp.R
import com.example.b2CFinancialApp.di.components.DaggerFragmentComponent
import com.example.b2CFinancialApp.di.components.FragmentComponent
import com.example.b2CFinancialApp.di.modules.FragmentModule
import javax.inject.Inject

abstract class BaseDialogFragment(private val layoutId: Int) : DialogFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var mDialogFragmentComponent: FragmentComponent? = null
    protected fun fragmentComponent(): FragmentComponent {
        if (mDialogFragmentComponent == null) {
            mDialogFragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(FragmentModule(this))
                    .activityComponent((activity as BaseActivity).activityComponent())
                    .build()
        }

        return mDialogFragmentComponent as FragmentComponent
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId,container,false)
    }

}