package com.omisoft.myapplication.mvvm.ui.countries

class CountriesModelImpl : CountriesModel {
    override fun getCountries(): List<String> = listOf("Belarus", "Ukraine", "Poland")
}