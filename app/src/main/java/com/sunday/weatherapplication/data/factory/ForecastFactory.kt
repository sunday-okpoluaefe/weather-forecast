package com.sunday.weatherapplication.data.factory

import com.sunday.weatherapplication.data.model.*
import com.sunday.weatherapplication.data.remote.ForecastResponse


/*
 * mock data for testing
 */

class ForecastFactory {
    companion object {
        fun forecast() = ForecastResponse(
            base = "",
            clouds = Clouds(all = 12),
            cod = 10,
            coord = Coord(lat = 7.094, lon = 3.9845),
            dt = 8338493,
            id = 34,
            main = Main(feelsLike = 45.0, grndLevel = 34, humidity = 45, pressure = 23, seaLevel = 20, temp = 78.09, tempMax = 120.0, tempMin = 23.09),
            name = "Abuja",
            sys = Sys(country = "Nigeria", sunrise = 23, sunset = 45),
            timezone = 360,
            visibility = 45,
            weather = listOf(WeatherFactory.weather()),
            wind = Wind(deg = 45, gust = 23.9, speed = 21.43)
        )
    }
}