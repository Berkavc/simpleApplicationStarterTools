package com.example.b2CFinancialApp.ui.signup

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.b2CFinancialApp.di.qualifers.ActivityContext
import com.example.b2CFinancialApp.room.AppDatabase
import com.example.b2CFinancialApp.utils.default
import com.example.b2CFinancialApp.viewmodel.BaseViewModel
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
        @param:ActivityContext private val context: Context,
        private val db: AppDatabase
) : BaseViewModel() {

    var passwordCheck = MutableLiveData<Boolean>().default(false)
    var termsOfUseCheck = MutableLiveData<Boolean>().default(false)
    var rememberMeCheck = MutableLiveData<Boolean>().default(false)

    var termsOfUseClicked = MutableLiveData<Boolean>().default(false)
    var signUpClicked = MutableLiveData<Boolean>().default(false)
    var marketingPermissionClicked = MutableLiveData<Boolean>().default(false)


    fun termsOfUseClicked() {
        termsOfUseClicked.postValue(true)
    }

    fun signUpClicked() {
        signUpClicked.postValue(true)
    }

    fun marketingPermissionClicked() {
        marketingPermissionClicked.postValue(true)
    }

}