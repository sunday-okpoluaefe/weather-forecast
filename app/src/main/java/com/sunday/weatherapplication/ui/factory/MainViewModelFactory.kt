package com.sunday.weatherapplication.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sunday.weatherapplication.service.ApiClient
import com.sunday.weatherapplication.ui.city_fragment.CityViewModel
import com.sunday.weatherapplication.ui.forecast_fragment.ForecastViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val client: ApiClient): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(CityViewModel::class.java)) return CityViewModel() as T
        if(modelClass.isAssignableFrom(ForecastViewModel::class.java)) return ForecastViewModel(client) as T

        throw IllegalArgumentException("Invalid view model class")
    }
}