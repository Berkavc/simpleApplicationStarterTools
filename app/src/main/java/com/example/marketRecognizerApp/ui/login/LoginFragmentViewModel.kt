package com.example.marketRecognizerApp.ui.login

import android.content.Context
import com.example.marketRecognizerApp.di.qualifiers.RetrofitClientBuilder
import com.example.marketRecognizerApp.di.qualifiers.RoomDbBuilder
import com.example.marketRecognizerApp.models.ApiModels
import com.example.marketRecognizerApp.models.RoomModels
import com.example.marketRecognizerApp.retrofit.RetrofitInterface
import com.example.marketRecognizerApp.room.AppDatabase
import com.example.marketRecognizerApp.utils.CustomMutableSingleLiveData
import com.example.marketRecognizerApp.utils.default
import com.example.marketRecognizerApp.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    @RoomDbBuilder private val db: AppDatabase,
    @RetrofitClientBuilder private val retrofitInterFace: RetrofitInterface
) : BaseViewModel() {

    var isLoginClicked = CustomMutableSingleLiveData<Boolean>().default(false)

    var isSignUpClicked = CustomMutableSingleLiveData<Boolean>().default(false)

    var isForgotPasswordClicked = CustomMutableSingleLiveData<Boolean>().default(false)

    var isBioClicked = CustomMutableSingleLiveData<Boolean>().default(false)

    var loginResponseModel = CustomMutableSingleLiveData<ApiModels.LoginModel>().default(null)

    var localLoginResponseModel = CustomMutableSingleLiveData<RoomModels.LoginModel>().default(null)

    fun loginClicked() {
        isLoginClicked.postValue(true)
    }

    fun signUpClicked() {
        isSignUpClicked.postValue(true)
    }

    fun forgotPasswordClicked() {
        isForgotPasswordClicked.postValue(true)
    }

    fun bioClicked() {
        isBioClicked.postValue(true)
    }

    fun saveCheckBoxState(
        userName: String? = null,
        rememberMe: Boolean = false
    ) {
        val coroutineCallCheckBox = CoroutineScope(Dispatchers.IO)
        coroutineCallCheckBox.async {
            db.roomDao().insertLoginTable(
                RoomModels.LoginModel(
                    userName = userName,
                    rememberMe = rememberMe
                )
            )
        }
    }

    fun refreshCheckBoxState() {
        val coroutineCallCheckBox = CoroutineScope(Dispatchers.IO)
        coroutineCallCheckBox.async {
            db.roomDao().insertLoginTable(
                RoomModels.LoginModel(
                    userName = null,
                    rememberMe = false
                )
            )
        }
    }

    fun gatherCheckBoxState(){
        val coroutineCallCheckBox = CoroutineScope(Dispatchers.IO)
        coroutineCallCheckBox.async {
            localLoginResponseModel.postValue(db.roomDao().getLoginTable())
        }
    }

    fun controlCredentials(userName: String? = null, rememberMe: Boolean = false) {
        if (!userName.isNullOrEmpty()) {
            //Go to cloud db and check user if exist or not !!


            //-----------
            //If Exists and will return a user Response object.
            loginResponseModel.postValue(
                ApiModels.LoginModel(
                    userName = "berk",
                    password = "12345",
                    rememberMe = rememberMe,
                    biometricObject = null
                )
            )
        } else {
            //error empty state show generic error dialog!!
        }
    }

}