package com.leaf.domain.mapper

import com.leaf.domain.model.WeatherRequest
import com.leaf.repository.model.ForecastRequest

object RequestMapper : Mapper<WeatherRequest,ForecastRequest> {
    override fun mapFromModel(type: ForecastRequest?): WeatherRequest? {
        return type?.let {
              WeatherRequest(
                 latitude = it.latitude,
                 longitude = it.longitude,
                 cityName = it.cityName,
                 dateTime = it.dateTime,
                 fetchFromApi = it.fetchFromApi)
        }
    }

    override fun mapToModel(type: WeatherRequest?): ForecastRequest? {
        return type?.let {
            ForecastRequest(
                latitude = it.latitude,
                longitude = it.longitude,
                cityName = it.cityName,
                dateTime = it.dateTime,
                fetchFromApi = it.fetchFromApi)
        }
    }
}