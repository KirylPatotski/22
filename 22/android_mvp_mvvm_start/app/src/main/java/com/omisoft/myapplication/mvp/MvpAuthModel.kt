package com.omisoft.myapplication.mvp

interface MvpAuthModel {
    fun onLoginClicked(email: String, password: String): Boolean
}