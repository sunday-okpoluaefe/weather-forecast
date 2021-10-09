package com.sunday.weatherapplication.ui.city_fragment

import androidx.lifecycle.*
import com.sunday.weatherapplication.data.factory.CityFactory
import com.sunday.weatherapplication.data.model.City
import com.sunday.weatherapplication.service.ApiClient
import kotlinx.coroutines.launch

class CityViewModel (): ViewModel(),
    LifecycleObserver {

    private var _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> = _cities

    init {
        _cities.value = CityFactory.cities()
    }
}