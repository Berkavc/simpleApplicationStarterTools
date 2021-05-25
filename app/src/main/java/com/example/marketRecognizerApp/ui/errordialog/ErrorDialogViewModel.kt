package com.example.marketRecognizerApp.ui.errordialog

import com.example.marketRecognizerApp.utils.CustomMutableSingleLiveData
import com.example.marketRecognizerApp.utils.default
import com.example.marketRecognizerApp.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class ErrorDialogViewModel : BaseViewModel() {
    var genericButtonClicked = CustomMutableSingleLiveData<Boolean>().default(false)

    fun genericButtonClicked() {
        genericButtonClicked.postValue(true)
    }
}