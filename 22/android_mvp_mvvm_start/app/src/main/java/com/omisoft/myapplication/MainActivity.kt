package com.omisoft.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.omisoft.myapplication.mvvm.ui.auth.fragment.AuthFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
        const val NAVIGATION_EVENT = "navigation_event"
        const val NAVIGATION_EVENT_DATA_KEY = "navigation_event_data_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listenNavigationEvents()
        openFragment(AuthFragment(), tag = "AuthFragment")
    }

    fun openFragment(fragment: Fragment, doClearBackStack: Boolean = false, tag: String? = null) {
        if (doClearBackStack) {
            clearBackStack()
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    fun findFragmentByTag(tag: String): Fragment? = supportFragmentManager.findFragmentByTag(tag)

    override fun onBackPressed() {
        val fragmentCount = supportFragmentManager.backStackEntryCount
        if (fragmentCount > 1) {
            super.onBackPressed()
        } else {
            finish()
        }
    }

    private fun clearBackStack() = supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

    private fun listenNavigationEvents() {
        supportFragmentManager.setFragmentResultListener(NAVIGATION_EVENT, this) { _, bundle ->
            val navigationEvent = bundle.get(NAVIGATION_EVENT_DATA_KEY) as String
            Log.d(TAG, navigationEvent)
        }
    }
}