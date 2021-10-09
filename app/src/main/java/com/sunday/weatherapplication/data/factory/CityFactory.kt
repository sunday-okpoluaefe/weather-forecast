package com.sunday.weatherapplication.data.factory

import com.sunday.weatherapplication.data.model.City

class CityFactory {

    companion object {
        fun cities() = listOf(
            City(country = "Kenya", name = "Kenya"),
            City(country = "Egypt", name = "Cairo"),
            City(country = "Nigeria", name = "Lagos"),
            City(country = "Nigeria", name = "Abuja"),
            City(country = "United States", name = "New York"),
            City(country = "United States", name = "Texas"),
            City(country = "Brazil", name = "Amazon"),
            City(country = "Belarus", name = "Belarus"),
            City(country = "Lesotho", name = "Lesotho"),
            City(country = "Indonesia", name = "Jakarta"),
            City(country = "Turkey.", name = "Ankara"),
            City(country = "Nigeria", name = "Kano"),
            City(country = "Peru", name = "Peru"),
            City(country = "Canada", name = "Winnipeg"),
            City(country = "Iraq", name = "Baghdad"),
            City(country = "London", name = "WestHam")
        )
    }
}