package com.sunday.weatherapplication.ui.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunday.weatherapplication.data.remote.ForecastResponse
import com.sunday.weatherapplication.service.ApiClient
import kotlinx.coroutines.launch
import java.lang.Exception

class ForecastViewModel (private val client: ApiClient): ViewModel() {

    private var _forecast = MutableLiveData<ForecastResponse>()
    val forecast: LiveData<ForecastResponse> = _forecast

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun getForecast(city: String){
        viewModelScope.launch {
            try {
                _loading.value = true
                val result = client.getForeCast(location = city)
                _forecast.value = result

                _loading.value = false
            }catch (error: Exception){
                _loading.value = false
                _error.value = error.message
            }
        }
    }
}