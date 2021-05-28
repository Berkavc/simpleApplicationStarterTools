package com.example.marketRecognizerApp.ui.map

import android.content.Context
import com.example.marketRecognizerApp.di.qualifiers.RetrofitClientBuilder
import com.example.marketRecognizerApp.di.qualifiers.RoomDbBuilder
import com.example.marketRecognizerApp.retrofit.RetrofitInterface
import com.example.marketRecognizerApp.room.AppDatabase
import com.example.marketRecognizerApp.utils.CustomMutableSingleLiveData
import com.example.marketRecognizerApp.utils.default
import com.example.marketRecognizerApp.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MapFragmentViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    @RoomDbBuilder private val db: AppDatabase,
    @RetrofitClientBuilder private val retrofitInterFace: RetrofitInterface
) : BaseViewModel() {


}