package com.omisoft.myapplication.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.omisoft.myapplication.MainActivity
import com.omisoft.myapplication.R

class SuccessFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().supportFragmentManager.setFragmentResult(
            MainActivity.NAVIGATION_EVENT,
            bundleOf(MainActivity.NAVIGATION_EVENT_DATA_KEY to "SuccessFragment Created")
        )

        val successFlagImageView = view.findViewById<AppCompatImageView>(R.id.success_flag)
        val successMessageTextView = view.findViewById<TextView>(R.id.success_message)

        val flag = ContextCompat.getDrawable(requireContext(), R.drawable.flag_of_belarus)
        val redColor = ContextCompat.getColor(requireContext(), R.color.red)

        successFlagImageView.setImageDrawable(flag)
        successMessageTextView.setTextColor(redColor)
    }
}