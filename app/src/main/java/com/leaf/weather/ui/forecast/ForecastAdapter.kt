package com.leaf.weather.ui.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leaf.weather.R
import com.leaf.weather.databinding.ListItemForecastBinding

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    private val weatherForecast = ArrayList<WeatherForecastReport>()

    fun setItems(items: List<WeatherForecastReport>) {
        this.weatherForecast.clear()
        this.weatherForecast.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_forecast, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ForecastAdapter.ViewHolder, position: Int) {
        holder.update(weatherForecast[position])
    }

    override fun getItemCount(): Int {
        return weatherForecast.size
    }


    inner class ViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemForecastBinding.bind(itemView)
        fun update(forecast: WeatherForecastReport) {
            binding.let {

                it.dayOfWeek.text = forecast.dayOfWeek
                it.icon.setImageResource(forecast.icon)
                it.tempValue.text =
                    "${forecast.temp} ${itemView.context.getString(forecast.measurement)}"
            }
        }
    }
}
