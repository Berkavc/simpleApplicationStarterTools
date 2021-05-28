package com.example.marketRecognizerApp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.security.Signature

@HiltAndroidApp
class MarketRecognizerAppApplication: Application()  {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val appSignatureHelper = AppSignatureHelper(this)
        appSignatureHelper.appSignatures.forEach {
            Timber.e("signs:${it}")
        }
        val sign = Signature.getInstance("SHA256withRSA")
        Timber.e("sign_1${sign}")
    }
}