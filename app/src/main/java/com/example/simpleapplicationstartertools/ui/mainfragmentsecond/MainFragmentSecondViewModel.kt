package com.example.simpleapplicationstartertools.ui.mainfragmentsecond

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.simpleapplicationstartertools.di.qualifers.ActivityContext
import com.example.simpleapplicationstartertools.retrofit.RetrofitInterface
import com.example.simpleapplicationstartertools.room.AppDatabase
import com.example.simpleapplicationstartertools.utils.default
import com.example.simpleapplicationstartertools.viewmodel.BaseViewModel
import javax.inject.Inject

class MainFragmentSecondViewModel @Inject constructor(
    @param:ActivityContext private val context: Context,
    private val db: AppDatabase,
    private val retrofitInterFace: RetrofitInterface
) : BaseViewModel() {
    var mainTitle =
        MutableLiveData<String>().default("Main Second fragment!!")
}