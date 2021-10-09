package com.sunday.weatherapplication.data.model


import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("grnd_level")
    val grndLevel: Int?,
    val humidity: Int?,
    val pressure: Int?,
    @SerializedName("sea_level")
    val seaLevel: Int?,
    val temp: Double?,
    @SerializedName("temp_max")
    val tempMax: Double?,
    @SerializedName("temp_min")
    val tempMin: Double?
) : Parcelable