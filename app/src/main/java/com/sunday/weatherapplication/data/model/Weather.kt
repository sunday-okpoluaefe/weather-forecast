package com.sunday.weatherapplication.data.model

import android.os.Parcelable
import com.sunday.weatherapplication.util.WEATHER_API_ICON_URL
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val description: String?,
    val icon: String?,
    val id: Int?,
    val main: String?
) : Parcelable{
    fun iconUrl() = icon?.let { "$WEATHER_API_ICON_URL$it@4x.png" }
    fun iconUrlNormal() = icon?.let { "$WEATHER_API_ICON_URL$it@2x.png" }
}