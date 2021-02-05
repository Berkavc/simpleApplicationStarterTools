package com.example.b2CFinancialApp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.b2CFinancialApp.ui.mainactivity.MainActivityViewModel
import com.example.b2CFinancialApp.ui.login.LoginFragmentViewModel
import com.example.b2CFinancialApp.ui.popups.signup.TermsOfUseFragment
import com.example.b2CFinancialApp.ui.popups.signup.TermsOfUseViewModel
import com.example.b2CFinancialApp.ui.signup.SignUpViewModel
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
    @ViewModelKey(LoginFragmentViewModel::class)
    abstract fun bindsMainFragmentViewModel(loginFragmentViewModel: LoginFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindsMainFragmentSecondViewModel(signUpViewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TermsOfUseViewModel::class)
    abstract fun bindsTermsOfUseViewModel(termsOfUseViewModel: TermsOfUseViewModel): ViewModel

}