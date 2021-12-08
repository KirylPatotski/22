package com.omisoft.myapplication.mvvm.ui.countries

import androidx.lifecycle.*

class CountriesViewModel : ViewModel(), LifecycleEventObserver {

    val countriesLiveData = MutableLiveData<List<String>>()

    val model: CountriesModel = CountriesModelImpl()

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                println("ON_CREATE")
                getCountries()
            }
            Lifecycle.Event.ON_START -> {
                println("ON_START")
            }
            Lifecycle.Event.ON_RESUME -> {
                println("ON_RESUME")
            }
            Lifecycle.Event.ON_PAUSE -> {
                println("ON_PAUSE")
            }
            Lifecycle.Event.ON_STOP -> {
                println("ON_STOP")
            }
            Lifecycle.Event.ON_DESTROY -> {
                println("ON_DESTROY")
            }
            Lifecycle.Event.ON_ANY -> {
                println("ON_ANY")
            }
        }
    }

    private fun getCountries() {
        val countries = model.getCountries()
        countriesLiveData.value = countries
    }

    override fun onCleared() {
        super.onCleared()
        println("CLEARED")
    }
}