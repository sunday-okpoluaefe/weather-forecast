package com.sunday.weatherapplication.service

import com.sunday.weatherapplication.data.remote.ForecastResponse

interface ApiClient {
    suspend fun getForeCast(location: String): ForecastResponse
}