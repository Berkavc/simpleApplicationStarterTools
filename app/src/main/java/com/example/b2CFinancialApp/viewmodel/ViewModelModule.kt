package com.example.b2CFinancialApp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.b2CFinancialApp.ui.mainactivity.MainActivityViewModel
import com.example.b2CFinancialApp.ui.mainfragment.MainFragmentViewModel
import com.example.b2CFinancialApp.ui.mainfragmentsecond.MainFragmentSecondViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/*
 *This is a viewModel module class you need to write your new viewModels in this class for binding them.
 */

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindsMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindsMainFragmentViewModel(mainFragmentViewModel: MainFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentSecondViewModel::class)
    abstract fun bindsMainFragmentSecondViewModel(mainFragmentSecondViewModel: MainFragmentSecondViewModel): ViewModel

}