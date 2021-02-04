package com.example.b2CFinancialApp.ui.mainfragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.b2CFinancialApp.di.qualifers.ActivityContext
import com.example.b2CFinancialApp.room.AppDatabase
import com.example.b2CFinancialApp.utils.default
import com.example.b2CFinancialApp.viewmodel.BaseViewModel
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
        @param:ActivityContext private val context: Context,
        private val db: AppDatabase
) : BaseViewModel() {
    var mainTitle =
        MutableLiveData<String>().default("Main fragment!!")
}