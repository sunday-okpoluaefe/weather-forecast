package com.sunday.weatherapplication.data.factory

import com.sunday.weatherapplication.data.model.Weather

/*
 * mock data for testing
 */
class WeatherFactory {
    companion object {
        fun weather() = Weather(description = "light clouds", icon = "10d", id = 32, main = "Clouds")
    }
}