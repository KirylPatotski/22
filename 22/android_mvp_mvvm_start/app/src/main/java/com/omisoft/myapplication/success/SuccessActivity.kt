package com.omisoft.myapplication.success

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.omisoft.myapplication.R

class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_success)

        val successFlagImageView = findViewById<AppCompatImageView>(R.id.success_flag)
        val successMessageTextView = findViewById<TextView>(R.id.success_message)

        val flag = ContextCompat.getDrawable(this, R.drawable.flag_of_belarus)
        val redColor = ContextCompat.getColor(this, R.color.red)

        successFlagImageView.setImageDrawable(flag)
        successMessageTextView.setTextColor(redColor)
    }
}