package com.sunday.weatherapplication.data.remote

import com.sunday.weatherapplication.service.ApiClient
import com.sunday.weatherapplication.service.ForecastService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ForecastApiClient @Inject constructor(private val service: ForecastService): ApiClient {
    override suspend fun getForeCast(location: String): ForecastResponse {
        return withContext(IO){
            service.getForeCast(location = location)
        }
    }
}