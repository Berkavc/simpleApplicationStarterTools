package com.example.simpleapplicationstartertools.ui.mainfragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.simpleapplicationstartertools.di.qualifers.ActivityContext
import com.example.simpleapplicationstartertools.models.DummyModels
import com.example.simpleapplicationstartertools.retrofit.RetrofitClient
import com.example.simpleapplicationstartertools.retrofit.RetrofitInterface
import com.example.simpleapplicationstartertools.room.AppDatabase
import com.example.simpleapplicationstartertools.utils.default
import com.example.simpleapplicationstartertools.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    @param:ActivityContext private val context: Context,
    private val db: AppDatabase,
    private val retrofitInterFace: RetrofitInterface
) : BaseViewModel() {
    var mainTitle =
        MutableLiveData<String>().default("Main fragment!!")
}