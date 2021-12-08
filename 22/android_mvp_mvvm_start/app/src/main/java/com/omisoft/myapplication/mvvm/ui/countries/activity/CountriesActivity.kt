package com.omisoft.myapplication.mvvm.ui.countries.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.omisoft.myapplication.R
import com.omisoft.myapplication.mvvm.ui.countries.CountriesViewModel

class CountriesActivity : AppCompatActivity() {

    private lateinit var viewModel: CountriesViewModel
    private lateinit var countriesListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_countries)
        viewModel = ViewModelProvider(this)[CountriesViewModel::class.java]
        lifecycle.addObserver(viewModel)

        countriesListView = findViewById(R.id.list_countries)
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.countriesLiveData.observe(this, { countries ->
            val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, countries)
            countriesListView.adapter = adapter
        })
    }
}