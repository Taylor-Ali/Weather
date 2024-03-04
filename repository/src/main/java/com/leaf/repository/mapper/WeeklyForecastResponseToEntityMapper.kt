package com.leaf.repository.mapper

import com.leaf.cache.database.entities.WeeklyForecastEntity
import com.leaf.data.response.WeeklyForecastResponse
import com.leaf.repository.model.City
import com.leaf.repository.model.Coord
import com.leaf.repository.model.WeeklyForecastReport
import java.util.Date

object WeeklyForecastResponseToEntityMapper : Mapper<WeeklyForecastResponse,WeeklyForecastEntity> {
    override fun mapFromModel(type: WeeklyForecastEntity?): WeeklyForecastResponse? {
        TODO("Not yet implemented")
    }

    override fun mapToModel(type: WeeklyForecastResponse?): WeeklyForecastEntity? {
        return try {
            type?.let {
                WeeklyForecastEntity(
                    id = null,
                    cod = it.cod,
                    message = it.message,
                    cnt = it.cnt,
                    forecast = it.list.map {currentForecastResponse->
                        WeatherResponseToForecastMapper.mapFromModel(currentForecastResponse)!!
                    },
                    cityName = it.city.name,
                    dateTime = Date().time

                )
            }
        } catch (e: Exception) {
            e.message
            return null
        }
    }
}