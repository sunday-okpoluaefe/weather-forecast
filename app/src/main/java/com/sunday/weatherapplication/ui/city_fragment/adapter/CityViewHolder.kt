package com.sunday.weatherapplication.ui.city_fragment.adapter

import android.view.LayoutInflater
import android.view.TouchDelegate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunday.weatherapplication.data.model.City
import com.sunday.weatherapplication.databinding.CityItemLayoutBinding

class CityViewHolder(private val binding: CityItemLayoutBinding, private val delegate: Delegate) : RecyclerView.ViewHolder(binding.root){

    interface Delegate {
       fun onClick(city: City)
    }

    fun bind(city: City){
        binding.city.text = city.name
        binding.country.text = city.country
        binding.container.setOnClickListener {
            this.delegate.onClick(city)
        }
    }

    companion object {
        fun from(parent: ViewGroup, delegate: Delegate): CityViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = CityItemLayoutBinding.inflate(inflater, parent, false)
            return CityViewHolder(binding, delegate)
        }
    }
}