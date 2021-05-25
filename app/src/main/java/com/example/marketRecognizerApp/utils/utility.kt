package com.example.marketRecognizerApp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.view.View
import androidx.databinding.BindingAdapter
import com.example.marketRecognizerApp.ui.errordialog.ErrorDialogActivity

fun initializeErrorDialogPopup(
    message: String? = null,
    buttonMessage: String? = null,
    context: Context
) {
    context.let {
        val intent = Intent(it, ErrorDialogActivity::class.java)
        intent.putExtra("message", message)
        intent.putExtra("buttonMessage", buttonMessage)
        (context as Activity).startActivity(intent)
    }
}

fun checkEmailFormat(email: String?): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@BindingAdapter("android:throttleClick")
fun View.clickWithThrottle(action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        private val throttleTime = 1250L

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < throttleTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

