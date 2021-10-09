package com.sunday.weatherapplication.ui.city_fragment.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sunday.weatherapplication.data.model.City

class CityAdapter(private val delegate: CityViewHolder.Delegate) : RecyclerView.Adapter<CityViewHolder>() {

    private var data: List<City> = ArrayList()

    fun load(cities: List<City>) {
        val list = data
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            CityAdapterUtil(
                list,
                cities
            )
        )

        data = cities
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder = CityViewHolder.from(parent, delegate)

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}