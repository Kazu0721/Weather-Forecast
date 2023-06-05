package com.example.weatherforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherforecast.databinding.RowListBinding

val DIFF_UTIL_ITEM_CALLBACK = object : DiffUtil.ItemCallback<User>() {
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
}


class WeatherAdapter: ListAdapter<User, WeatherAdapter.RecyclerViewHolder>(DIFF_UTIL_ITEM_CALLBACK){

    inner class RecyclerViewHolder(var binding: RowListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindWeather(state: User) {
            binding.dates.text = state.date
            binding.description.text = state.description
            binding.temp.text = state.temp.toString()
            binding.humidity.text = state.humidity.toString()
            binding.pressure.text = state.pressure.toString()
            binding.icon.load(state.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = RowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val state = getItem(position)
        holder.bindWeather(state)
    }
}