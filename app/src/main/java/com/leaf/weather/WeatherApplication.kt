package com.leaf.weather

import android.app.Application
import com.google.android.libraries.places.api.Places
import com.leaf.utils.BuildConfigProvider
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        BuildConfigProvider.apiUrl = BuildConfig.API_URL
        BuildConfigProvider.apiKey = BuildConfig.API_KEY

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, BuildConfig.API_PLACES_API_KEY)
        }

        Places.createClient(applicationContext)
    }
}