package com.sunday.weatherapplication

import com.sunday.weatherapplication.data.factory.ForecastFactory
import com.sunday.weatherapplication.data.remote.ForecastResponse
import com.sunday.weatherapplication.service.ApiClient

open class MockForecastApiClient: ApiClient {
    override suspend fun getForeCast(location: String): ForecastResponse {
        return ForecastFactory.forecast()
    }
}