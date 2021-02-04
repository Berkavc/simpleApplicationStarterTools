package com.example.b2CFinancialApp.ui.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.b2CFinancialApp.Constants
import com.example.b2CFinancialApp.di.qualifers.ActivityContext
import com.example.b2CFinancialApp.models.login.LoginState
import com.example.b2CFinancialApp.models.room.Models
import com.example.b2CFinancialApp.room.AppDatabase
import com.example.b2CFinancialApp.utils.default
import com.example.b2CFinancialApp.viewmodel.BaseViewModel
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.safetynet.SafetyNet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import timber.log.Timber
import javax.inject.Inject

class LoginFragmentViewModel @Inject constructor(
    @param:ActivityContext private val context: Context,
    private val db: AppDatabase
) : BaseViewModel() {
    var isLoginClicked =
        MutableLiveData<Boolean>().default(false)
    var isSignUpClicked =
        MutableLiveData<Boolean>().default(false)
    var isForgotPasswordClicked =
        MutableLiveData<Boolean>().default(false)
    var isBioClicked =
        MutableLiveData<Boolean>().default(false)

    var isRefreshLoginPageState =
        MutableLiveData<Boolean>().default(false)

    var captchaResult = MutableLiveData<Boolean>().default(true)

    var loginTryCounter = MutableLiveData<Int>().default(0)

    var controlSignUpState = MutableLiveData<LoginState>().default(LoginState.NOT_CHECKED)

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

    fun controlCredentials(userName: String, password: String): Boolean {
        if (userName == "05312874647" && password == "12345") {
            return true
        }
        return false
    }

    fun controlCaptcha() {
        SafetyNet.getClient(context).verifyWithRecaptcha(Constants.CAPTCHA_API_KEY)
            .addOnSuccessListener { response ->
                // Indicates communication with reCAPTCHA service was
                // successful.
                val userResponseToken = response.tokenResult
                if (response.tokenResult?.isNotEmpty() == true) {
                    // Validate the user response token using the
                    // reCAPTCHA siteverify API.
                    captchaResult.postValue(true)
                }
            }
            .addOnFailureListener { e ->
                if (e is ApiException) {
                    // An error occurred when communicating with the
                    // reCAPTCHA service. Refer to the status code to
                    // handle the error appropriately.
                    Timber.e("Error: ${CommonStatusCodes.getStatusCodeString(e.statusCode)}")
                } else {
                    // A different, unknown type of error occurred.
                    Timber.e("Error: ${e.message}")
                }
            }
    }

    fun saveCheckBoxState(phoneNumber: String) {
        val coroutineCallCheckBox = CoroutineScope(Dispatchers.IO)
        coroutineCallCheckBox.async {
            db.roomDao().insertCheckBoxState(
                Models.LoginCheckBoxModel(isChecked = true, phoneNumber = phoneNumber)
            )
        }
    }

    fun refreshCheckBoxState() {
        val coroutineCallCheckBox = CoroutineScope(Dispatchers.IO)
        coroutineCallCheckBox.async {
            db.roomDao().insertCheckBoxState(
                Models.LoginCheckBoxModel(isChecked = false)
            )
        }
    }

    fun refreshCheckBoxStateOnRefreshState(){
        val coroutineCallCheckBox = CoroutineScope(Dispatchers.IO)
        coroutineCallCheckBox.async {
            db.roomDao().insertCheckBoxState(
                Models.LoginCheckBoxModel(isChecked = false)
            )
            isRefreshLoginPageState.postValue(true)
        }
    }

    fun controlCheckBoxState(): String? {
        return db.roomDao().getCheckBoxState().phoneNumber
    }

}