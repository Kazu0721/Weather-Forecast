package com.example.weatherforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.databinding.CityRowBinding

val DIFF_UTIL_CATEGORY_ITEM_CALLBACK = object: DiffUtil.ItemCallback<CityName>(){
    override fun areContentsTheSame(oldItem: CityName, newItem: CityName): Boolean {
        return oldItem == newItem
    }
    override fun areItemsTheSame(oldItem: CityName, newItem: CityName): Boolean {
        return oldItem.id == newItem.id
    }
}

class CityAdapter: ListAdapter<CityName, CityItemViewHolder>(DIFF_UTIL_CATEGORY_ITEM_CALLBACK){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityItemViewHolder {
        val view = CityRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: CityItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CityItemViewHolder(private val binding: CityRowBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: CityName) {
        binding.city.text = item.cityName
    }
}
