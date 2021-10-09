package com.sunday.weatherapplication.data.factory

import com.sunday.weatherapplication.data.model.Current
import com.sunday.weatherapplication.data.model.Rain

/*
 * mock data for testing
 */
class CurrentFactory {

    companion object {
        fun current() = Current(clouds = 23, dewPoint = 34.2,
            dt = 345433, feelsLike = 34.3,
            humidity = 45, pressure = 32, rain = Rain(h = 53.3),
            sunset = 43, sunrise = 43, temp = 45.32,
            uvi = 34.2, visibility = 44, weather = listOf(WeatherFactory.weather()),
            windDeg = 32, windGust = 43.3,  windSpeed = 21.5)
    }
}