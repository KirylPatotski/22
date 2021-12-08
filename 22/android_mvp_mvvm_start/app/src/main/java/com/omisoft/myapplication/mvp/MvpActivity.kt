package com.omisoft.myapplication.mvp

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import com.omisoft.myapplication.R
import com.omisoft.myapplication.success.SuccessActivity

class MvpActivity : AppCompatActivity(), MvpView {
    private lateinit var progress: ProgressBar
    private lateinit var overlay: FrameLayout
    private var testText: AppCompatTextView? = null

    private val presenter: MvpPresenter = MvpPresenter(this, MvpAuthModelImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)

        val buttonLogin: AppCompatButton = findViewById(R.id.button_login)
        val loginField: TextInputLayout = findViewById(R.id.input_layout_login)
        val passwordField: TextInputLayout = findViewById(R.id.input_layout_password)
        overlay = findViewById(R.id.overlay_container)
        progress = findViewById(R.id.progress)
        testText = findViewById(R.id.test_text)

        buttonLogin.setOnClickListener {
            progress.isVisible = true
            overlay.isVisible = true
            val loginText = loginField.editText?.text.toString()
            val passwordText = passwordField.editText?.text.toString()

            presenter.onLoginClicked(loginText, passwordText)
        }
    }

    override fun onLoginSuccess() {
        hideProgress()
        val intent = Intent(this, SuccessActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginFailed() {
        hideProgress()
        Toast.makeText(this, "Something went wrong. Please, retry!", Toast.LENGTH_LONG).show()
    }

    override fun updateText() {
        testText?.text = "Updated"
    }

    override fun onStop() {
        super.onStop()
        testText = null
    }

    private fun hideProgress() {
        progress.isVisible = false
        overlay.isVisible = false
    }
}