package com.example.simpleapplicationstartertools.di.components

import com.example.simpleapplicationstartertools.di.modules.FragmentModule
import com.example.simpleapplicationstartertools.di.scopes.PerFragment
import com.example.simpleapplicationstartertools.ui.mainfragment.MainFragment
import com.example.simpleapplicationstartertools.ui.mainfragmentsecond.MainFragmentSecond
import com.example.simpleapplicationstartertools.viewmodel.ViewModelModule
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
