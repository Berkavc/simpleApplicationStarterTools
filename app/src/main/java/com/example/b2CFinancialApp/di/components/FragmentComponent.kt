package com.example.b2CFinancialApp.di.components

import com.example.b2CFinancialApp.di.modules.FragmentModule
import com.example.b2CFinancialApp.di.scopes.PerFragment
import com.example.b2CFinancialApp.ui.login.LoginFragment
import com.example.b2CFinancialApp.ui.popups.signup.TermsOfUseFragment
import com.example.b2CFinancialApp.ui.signup.SignUpFragment
import com.example.b2CFinancialApp.viewmodel.ViewModelModule
import dagger.Component

@PerFragment
@Component(
    dependencies = [ActivityComponent::class],
    modules = [FragmentModule::class,
        ViewModelModule::class]
)
interface FragmentComponent {

    fun inject(fragment: LoginFragment)
    fun inject(fragment: SignUpFragment)
    fun inject(fragment: TermsOfUseFragment)
}
