package com.sunday.weatherapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sys(
    val country: String?,
    val sunrise: Int?,
    val sunset: Int?
) : Parcelable