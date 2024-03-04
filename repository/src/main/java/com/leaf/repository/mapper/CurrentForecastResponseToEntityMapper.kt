package com.leaf.repository.mapper


import com.leaf.cache.database.entities.CurrentWeatherForecastEntity
import com.leaf.cache.database.model.Cloud
import com.leaf.cache.database.model.Coord
import com.leaf.cache.database.model.Main
import com.leaf.cache.database.model.Wind
import com.leaf.data.response.CurrentForecastResponse


object CurrentForecastResponseToEntityMapper : Mapper<CurrentForecastResponse, CurrentWeatherForecastEntity> {
    override fun mapFromModel(type: CurrentWeatherForecastEntity?): CurrentForecastResponse? {
       return null
    }

    override fun mapToModel(type: CurrentForecastResponse?): CurrentWeatherForecastEntity? {
        return try {
            type?.let {
                CurrentWeatherForecastEntity(
                    id = it.id,
                    coord = Coord(
                        latitude = it.coord?.lat,
                        longitude = it.coord?.lon
                    ),
                    weather = it.weather.component1().let { weather ->
                        com.leaf.cache.database.model.Weather(
                            weather?.main,
                            weather?.description,
                            weather?.icon
                        )
                    },
                    base = it.base.orEmpty(),
                    main = Main(
                        temperature = it.main?.temp,
                        temperatureMax = it.main?.tempMax,
                        temperatureMin = it.main?.tempMin,
                        pressure = it.main?.pressure,
                        humidity = it.main?.humidity,
                        feelsLike = it.main?.feelsLike
                    ),
                    visibility = it.visibility,
                    wind = Wind(speed = it.wind.speed, degrees = it.wind.deg),
                    cloud = Cloud(all = it.cloud.all),
                    dateTime = it.dateTime,
                    sys = com.leaf.cache.database.model.Sys(
                        country = it.sys?.country,
                        sunrise = it.sys.sunrise,
                        sunset = it.sys.sunset,
                        type = it.sys.type
                    ),
                    timeZone = it.timeZone,
                    cityName = it.name,
                    cod = it.cod,
                    dateTimeText = it.dateTimeText

                )
            }
        } catch (e: Exception) {
            null
        }
    }
}

