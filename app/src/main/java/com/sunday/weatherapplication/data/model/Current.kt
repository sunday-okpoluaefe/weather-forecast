package com.sunday.weatherapplication.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Current(
    val clouds: Int?,
    @SerializedName("dew_point")
    val dewPoint: Double?,
    val dt: Int?,
    @SerializedName("feels_like")
    val feelsLike: Double?,
    val humidity: Int?,
    val pressure: Int?,
    val rain: Rain?,
    val sunrise: Int?,
    val sunset: Int?,
    val temp: Double?,
    val uvi: Double?,
    val visibility: Int?,
    val weather: List<Weather>?,
    @SerializedName("wind_deg")
    val windDeg: Int?,
    @SerializedName("wind_gust")
    val windGust: Double?,
    @SerializedName("wind_speed")
    val windSpeed: Double?
) : Parcelable