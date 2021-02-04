package com.example.b2CFinancialApp.utils

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager


fun showKeyboard(context: Context) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
}

fun hideKeyboard(context: Context) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}

fun View.clickWithThrottle(throttleTime: Long = 2000L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < throttleTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

/**
 * This class will prevent multiple clicks being dispatched.
 */
class ThrottleClickListener(private val onClickListener: View.OnClickListener) :
    View.OnClickListener {
    private var lastTime: Long = 0

    override fun onClick(v: View?) {
        val current = System.currentTimeMillis()
        if ((current - lastTime) > 2000L) {
            onClickListener.onClick(v)
            lastTime = current
        }
    }
}