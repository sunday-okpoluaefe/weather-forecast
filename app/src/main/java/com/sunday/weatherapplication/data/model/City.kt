package com.sunday.weatherapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City (val name: String,
    val country: String) : Parcelable