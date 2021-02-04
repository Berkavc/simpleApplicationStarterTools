package com.example.b2CFinancialApp.di.components

import com.example.b2CFinancialApp.di.modules.FragmentModule
import com.example.b2CFinancialApp.di.scopes.PerFragment
import com.example.b2CFinancialApp.ui.mainfragment.MainFragment
import com.example.b2CFinancialApp.ui.mainfragmentsecond.MainFragmentSecond
import com.example.b2CFinancialApp.viewmodel.ViewModelModule
import dagger.Component

@PerFragment
@Component(
    dependencies = [ActivityComponent::class],
    modules = [FragmentModule::class,
        ViewModelModule::class]
)
interface FragmentComponent {

    fun inject(fragment: MainFragment)
    fun inject(fragment: MainFragmentSecond)
}
