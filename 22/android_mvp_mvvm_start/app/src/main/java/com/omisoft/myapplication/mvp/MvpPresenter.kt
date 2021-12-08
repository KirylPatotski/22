package com.omisoft.myapplication.mvp

import android.os.Handler
import android.os.Looper

class MvpPresenter(private val view: MvpView, private val authModel: MvpAuthModel) {

    fun onLoginClicked(email: String, password: String) {
        Handler(Looper.getMainLooper()).postDelayed({
            val isSuccess = authModel.onLoginClicked(email, password)

            if (isSuccess) {
                view.onLoginSuccess()
            } else {
                view.onLoginFailed()
            }

            doLogRunningTask()
        }, 3000)
    }

    private fun doLogRunningTask() {
        Handler(Looper.getMainLooper()).postDelayed({
            view.updateText()
        }, 3000)
    }
}