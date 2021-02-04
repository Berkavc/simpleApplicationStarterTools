package com.example.b2CFinancialApp.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.b2CFinancialApp.B2CFinancialApplication.Companion.appComponent
import com.example.b2CFinancialApp.di.components.ActivityComponent
import com.example.b2CFinancialApp.di.components.DaggerActivityComponent
import com.example.b2CFinancialApp.di.modules.ActivityModule
import javax.inject.Inject

//Base Activity class inherit from this class in activities rather than AppCompatActivity class and feel free to customize your activity methods.
abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var mActivityComponent: ActivityComponent? = null
    fun activityComponent(): ActivityComponent {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .appComponent(appComponent)
                .build()
        }
        return mActivityComponent!!

    }
}