package com.example.b2CFinancialApp.ui.popups.signup

import android.content.Context
import com.example.b2CFinancialApp.di.qualifers.ActivityContext
import com.example.b2CFinancialApp.room.AppDatabase
import com.example.b2CFinancialApp.viewmodel.BaseViewModel
import javax.inject.Inject

class TermsOfUseViewModel@Inject constructor(
    @param:ActivityContext private val context: Context,
    private val db: AppDatabase
) : BaseViewModel()  {


}