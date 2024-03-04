package com.leaf.repository.mapper

import com.leaf.cache.database.entities.WeeklyForecastEntity
import com.leaf.cache.database.model.Forecast
import com.leaf.repository.model.City
import com.leaf.repository.model.WeatherReport
import com.leaf.repository.model.WeeklyForecastReport
import java.lang.Exception
import java.util.Date

object WeeklyForecastEntityMapper : Mapper<WeeklyForecastEntity,WeeklyForecastReport> {
    override fun mapFromModel(type: WeeklyForecastReport?): WeeklyForecastEntity? {
        return try{
            type?.let {
                WeeklyForecastEntity(
                    id = it.id?:0,
                    cod = it.cod,
                    message = it.message,
                    cnt = it.cnt,
                    forecast = it.list.map { weatherReport: WeatherReport? ->
                        ForecastToWeatherReportMapper.mapFromModel(weatherReport)!!
                    }, cityName = it.city?.name.orEmpty(),
                    dateTime = Date().time
                )
            }
        }catch (e : Exception){
            e.message
            return null
        }
    }

    override fun mapToModel(type: WeeklyForecastEntity?): WeeklyForecastReport? {
        return try{
            type?.let {
                WeeklyForecastReport(
                    id = it.id?:0,
                    cod = it.cod,
                    message = it.message,
                    cnt = it.cnt,
                    list = it.forecast?.map {forecast: Forecast ->
                                ForecastToWeatherReportMapper.mapToModel(forecast)!!
                    }.orEmpty(),
                    city = City(
                        id = null,
                        name = it.cityName.orEmpty(),
                        coord = null,
                        country = null,
                        sunset = null,
                        sunrise = null,
                        timezone = null)
                )
            }
        }catch (e : Exception){
            e.message
            return null
        }
    }
}