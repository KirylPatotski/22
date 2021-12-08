package com.omisoft.myapplication.mvp

interface MvpView {
    fun onLoginSuccess()
    fun onLoginFailed()
    fun updateText()
}