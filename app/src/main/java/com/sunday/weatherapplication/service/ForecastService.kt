package com.sunday.weatherapplication.service

import com.sunday.weatherapplication.data.remote.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET(value = "weather")
    suspend fun getForeCast(
        @Query(value = "q") location: String,
        @Query(value = "units") unit: String = "imperial"
    ) : ForecastResponse
}