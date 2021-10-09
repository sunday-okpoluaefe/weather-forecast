package com.sunday.weatherapplication.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.sunday.weatherapplication.MainCoroutineRule
import com.sunday.weatherapplication.MockForecastApiClient
import com.sunday.weatherapplication.R
import com.sunday.weatherapplication.data.factory.ForecastFactory
import com.sunday.weatherapplication.data.remote.ForecastResponse
import com.sunday.weatherapplication.ui.forecast.ForecastViewModel
import com.sunday.weatherapplication.util.extensions.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ForecastViewModelTest {
    private lateinit var viewModel: ForecastViewModel

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        viewModel = ForecastViewModel(MockForecastApiClient())
    }

    @Test
    fun test_getForecast_is_Successful(){
        this.viewModel.getForecast(city = "Abuja")
        val forecast = this.viewModel.forecast.getOrAwaitValue()
        assertThat(forecast).isEqualTo(ForecastFactory.forecast())
    }

    @Test
    fun test_getForecast_is_Loading_StopsOnSuccessful(){
        this.viewModel.getForecast(city = "Abuja")
        val loading = this.viewModel.loading.getOrAwaitValue()
        assertThat(loading).isEqualTo(false)
    }

    @Test
    fun test_onError_Show_ErrorMessage(){
        viewModel = ForecastViewModel(object: MockForecastApiClient(){
            override suspend fun getForeCast(location: String): ForecastResponse {
                throw Exception("error")
            }
        })

        this.viewModel.getForecast(city = "Abuja")
        val error = this.viewModel.error.getOrAwaitValue()
        assertThat(error).isEqualTo("error")
    }
}