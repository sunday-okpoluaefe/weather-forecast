package com.sunday.weatherapplication.data.remote

import android.os.Parcelable
import com.sunday.weatherapplication.data.model.*
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastResponse(
    val base: String?,
    val clouds: Clouds?,
    val cod: Int?,
    val coord: Coord?,
    val dt: Int?,
    val id: Int?,
    val main: Main?,
    val name: String?,
    val sys: Sys?,
    val timezone: Int?,
    val visibility: Int?,
    val weather: List<Weather>?,
    val wind: Wind?
) : Parcelable