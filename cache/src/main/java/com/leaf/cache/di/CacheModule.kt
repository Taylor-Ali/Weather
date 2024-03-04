package com.leaf.cache.di

import android.content.Context
import android.content.SharedPreferences
import com.leaf.cache.LocalDataSource
import com.leaf.cache.LocalDataSourceImpl
import com.leaf.cache.database.WeatherAppDatabase
import com.leaf.cache.preferences.WeatherSharedPreferences
import com.leaf.cache.preferences.WeatherSharedPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    private const val SHARED_PREFERENCES_NAME = "WeatherApp"

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences(
            SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun providesWeatherSharedPreference(sharedPreferences: SharedPreferences): WeatherSharedPreferences {
        return WeatherSharedPreferencesImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun providesDatabaseInstance(@ApplicationContext appContext: Context): WeatherAppDatabase {
        return WeatherAppDatabase.getInstance(appContext)
    }

    @Provides
    @Singleton
    fun providesCacheDataSource(
        database: WeatherAppDatabase,
        sharedPreferences: WeatherSharedPreferences
    ): LocalDataSource {
        return LocalDataSourceImpl(database, sharedPreferences)
    }
}
