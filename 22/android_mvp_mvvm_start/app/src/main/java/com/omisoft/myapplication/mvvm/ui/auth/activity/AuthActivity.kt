package com.omisoft.myapplication.mvvm.ui.auth.activity

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.omisoft.myapplication.R
import com.omisoft.myapplication.mvvm.ui.auth.AuthViewModel
import com.omisoft.myapplication.mvvm.ui.countries.activity.CountriesActivity


class AuthActivity : AppCompatActivity() {
    private lateinit var progress: ProgressBar
    private lateinit var overlay: FrameLayout
    private lateinit var viewModel: AuthViewModel
    private lateinit var loginField: TextInputLayout
    private lateinit var passwordField: TextInputLayout
    private lateinit var buttonLogin: AppCompatButton
    private var titleText: AppCompatTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_auth_mvvm)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        buttonLogin = findViewById(R.id.button_login)
        loginField = findViewById(R.id.input_layout_login)
        passwordField = findViewById(R.id.input_layout_password)
        overlay = findViewById(R.id.overlay_container)
        progress = findViewById(R.id.progress)
        titleText = findViewById(R.id.title_text)

        restoreValues()
        initListeners()
        subscribeOnLiveData()
    }

    private fun initListeners() {
        loginField.editText?.addTextChangedListener {
            viewModel.emailLiveData.value = it.toString()
        }
        passwordField.editText?.addTextChangedListener {
            viewModel.passwordLiveData.value = it.toString()
        }
        buttonLogin.setOnClickListener {
            val emailText = loginField.editText?.text.toString()
            val passwordText = passwordField.editText?.text.toString()
            viewModel.onLoginClicked(emailText, passwordText)
        }
    }

    private fun restoreValues() {
        loginField.editText?.setText(viewModel.emailLiveData.value ?: "")
        passwordField.editText?.setText(viewModel.passwordLiveData.value ?: "")
    }

    private fun subscribeOnLiveData() {
        viewModel.isLoginSuccessLiveData.observe(this, {
            val intent = Intent(this, CountriesActivity::class.java)
            startActivity(intent)
        })
        viewModel.isLoginFailedLiveData.observe(this, {
            Toast.makeText(this, "Something went wrong. Please, retry!", Toast.LENGTH_LONG).show()
        })
        viewModel.showProgressLiveData.observe(this, {
            showProgress()
        })
        viewModel.hideProgressLiveData.observe(this, {
            hideProgress()
        })
        viewModel.titleLiveData.observe(this, { title ->
            titleText?.text = title
        })
    }

    private fun hideProgress() {
        progress.isVisible = false
        overlay.isVisible = false
    }

    private fun showProgress() {
        progress.isVisible = true
        overlay.isVisible = true
    }
}