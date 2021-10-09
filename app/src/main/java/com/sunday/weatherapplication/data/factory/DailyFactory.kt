package com.sunday.weatherapplication.data.factory

import com.sunday.weatherapplication.data.model.Daily
import com.sunday.weatherapplication.data.model.FeelsLike
import com.sunday.weatherapplication.data.model.Temp

/*
 * mock data for testing
 */
class DailyFactory {
    companion object {
        fun daily() = Daily(
            clouds = 23,
            dewPoint = 23.4,
            dt = 898393,
            feelsLike = FeelsLike(day = 32.4, eve = 33.4, morn = 34.1, night = 22.54),
            humidity = 34,
            moonPhase = 22.4,
            moonrise = 32,
            moonset = 21,
            pop = 33.4,
            pressure = 22,
            rain = 44.4,
            sunrise = 22,
            sunset = 23,
            temp = Temp(day = 32.4, eve = 33.4, morn = 34.1, night = 22.54, max = 54.3, min = 34.4),
            uvi = 34.3,
            weather = listOf(WeatherFactory.weather()),
            windSpeed = 3.2,
            windGust = 33.4,
            windDeg = 3
        )
    }
}