package com.omisoft.myapplication.mvvm.ui.auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputLayout
import com.omisoft.myapplication.MainActivity
import com.omisoft.myapplication.R
import com.omisoft.myapplication.mvvm.ui.auth.AuthViewModel
import com.omisoft.myapplication.mvvm.ui.countries.fragment.CountriesFragment

class AuthFragment : Fragment() {

    private val viewModel by activityViewModels<AuthViewModel>()
    private lateinit var progress: ProgressBar
    private lateinit var overlay: FrameLayout
    private lateinit var loginField: TextInputLayout
    private lateinit var passwordField: TextInputLayout
    private var titleText: AppCompatTextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_auth_mvvm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().supportFragmentManager.setFragmentResult(
            MainActivity.NAVIGATION_EVENT,
            bundleOf(MainActivity.NAVIGATION_EVENT_DATA_KEY to "AuthFragment Created")
        )

        val buttonLogin: AppCompatButton = view.findViewById(R.id.button_login)
        loginField = view.findViewById(R.id.input_layout_login)
        passwordField = view.findViewById(R.id.input_layout_password)
        overlay = view.findViewById(R.id.overlay_container)
        progress = view.findViewById(R.id.progress)
        titleText = view.findViewById(R.id.title_text)

        restoreValues()

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

        subscribeOnLiveData()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun restoreValues() {
        loginField.editText?.setText(viewModel.emailLiveData.value ?: "")
        passwordField.editText?.setText(viewModel.passwordLiveData.value ?: "")
    }

    private fun subscribeOnLiveData() {
        viewModel.isLoginSuccessLiveData.observe(viewLifecycleOwner, {
            (activity as MainActivity).openFragment(CountriesFragment(), doClearBackStack = true)
        })
        viewModel.isLoginFailedLiveData.observe(viewLifecycleOwner, {
            Toast.makeText(context, "Something went wrong. Please, retry!", Toast.LENGTH_LONG).show()
        })
        viewModel.showProgressLiveData.observe(viewLifecycleOwner, {
            showProgress()
        })
        viewModel.hideProgressLiveData.observe(viewLifecycleOwner, {
            hideProgress()
        })
        viewModel.titleLiveData.observe(viewLifecycleOwner, { title ->
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