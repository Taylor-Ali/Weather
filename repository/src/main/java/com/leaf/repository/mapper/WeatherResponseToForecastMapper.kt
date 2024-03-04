package com.leaf.repository.mapper

import com.leaf.cache.database.model.Forecast
import com.leaf.data.response.CurrentForecastResponse

object WeatherResponseToForecastMapper : Mapper<Forecast, CurrentForecastResponse> {
    override fun mapFromModel(type: CurrentForecastResponse?): Forecast? {
        return try {
            type.let {
                Forecast(
                    id = it?.id ?: 0,
                    coord = com.leaf.cache.database.model.Coord(
                        latitude = it?.coord?.lat, longitude = it?.coord?.lon
                    ),
                    weather = it?.weather?.map { weather ->
                        com.leaf.cache.database.model.Weather(
                            main = weather.main,
                            description = weather.description,
                            icon = weather.icon
                        )
                    }.orEmpty(),
                    base = it?.base,
                    main = com.leaf.cache.database.model.Main(
                        temperature = it?.main?.temp,
                        temperatureMax = it?.main?.tempMax,
                        temperatureMin = it?.main?.tempMin,
                        pressure = it?.main?.pressure,
                        humidity = it?.main?.humidity,
                        feelsLike = it?.main?.feelsLike
                    ),
                    visibility = it?.visibility ?: 0,
                    wind = com.leaf.cache.database.model.Wind(
                        speed = it?.wind?.speed, degrees = it?.wind?.deg
                    ),
                    cloud = com.leaf.cache.database.model.Cloud(all = it?.cloud?.all),
                    dateTime = it?.dateTime ?: 0,
                    sys = com.leaf.cache.database.model.Sys(
                        country = it?.sys?.country,
                        sunrise = it?.sys?.sunrise,
                        sunset = it?.sys?.sunset,
                        type = it?.sys?.type
                    ),
                    timeZone = it?.timeZone ?: 0,
                    name = it?.name,
                    cod = it?.cod ?: 0,
                    dateTimeText = it?.dateTimeText ?: ""

                )
            }
        } catch (e: Exception) {
            null
        }
    }


    override fun mapToModel(type: Forecast?): CurrentForecastResponse? {
        return null
    }
}