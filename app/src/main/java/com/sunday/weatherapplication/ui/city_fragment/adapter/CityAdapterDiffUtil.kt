package com.sunday.weatherapplication.ui.city_fragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sunday.weatherapplication.data.model.City

class CityAdapterUtil(var old: List<City>, var new: List<City>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = new[newItemPosition].name == old[oldItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = new[newItemPosition] == old[oldItemPosition]
}