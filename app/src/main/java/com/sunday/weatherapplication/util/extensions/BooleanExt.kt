package com.sunday.weatherapplication.util.extensions

import android.view.View

fun Boolean.toVisibility() = when(this){
    true -> View.VISIBLE
    false -> View.GONE
}