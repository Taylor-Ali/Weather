package com.leaf.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.leaf.cache.database.converter.Converters
import com.leaf.cache.database.dao.CityDao
import com.leaf.cache.database.dao.CurrentWeatherForecastDao
import com.leaf.cache.database.dao.WeeklyWeatherForecastDao
import com.leaf.cache.database.entities.CityEntity
import com.leaf.cache.database.entities.CurrentWeatherForecastEntity
import com.leaf.cache.database.entities.WeeklyForecastEntity

@Database(
    entities = [CurrentWeatherForecastEntity::class, WeeklyForecastEntity::class, CityEntity::class], version = 1
)
@TypeConverters(Converters::class)
abstract class WeatherAppDatabase : RoomDatabase() {
    abstract fun currentForecastDao(): CurrentWeatherForecastDao
    abstract fun weeklyWeatherForecastDao(): WeeklyWeatherForecastDao
    abstract fun cityDao(): CityDao

    companion object {
        private var instance: WeatherAppDatabase? = null
        private const val DB_NAME = "weather_database"

        @Synchronized
        fun getInstance(ctx: Context): WeatherAppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, WeatherAppDatabase::class.java,
                    DB_NAME
                )
                    .addTypeConverter(Converters())
                    .fallbackToDestructiveMigration()
                    .build()

            return instance!!

        }
    }
}