package com.leaf.weather.ui.cities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leaf.weather.R
import com.leaf.weather.databinding.ListItemCityBinding

class CityAdapter(private val selectedItemListener: CityItemListener) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    private val cities = ArrayList<City>()

    fun setItems(items: List<City>) {
        this.cities.clear()
        this.cities.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_city, parent, false), selectedItemListener
        )
    }

    override fun onBindViewHolder(holder: CityAdapter.ViewHolder, position: Int) {
        holder.update(cities[position])
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    interface CityItemListener {
        fun onClickedCity(city: City)
        fun onDeleteItemClick(city: City)
    }

    inner class ViewHolder(
        itemView: View,
        private val cityItemListener: CityItemListener = selectedItemListener
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemCityBinding.bind(itemView)
        fun update(city: City) {
            binding.cityName.text = city.name

            binding.cityItemLayout.setOnClickListener { cityItemListener.onClickedCity(city) }
            binding.iconDelete.setOnClickListener {
                cityItemListener.onDeleteItemClick(city)
            }
        }
    }
}