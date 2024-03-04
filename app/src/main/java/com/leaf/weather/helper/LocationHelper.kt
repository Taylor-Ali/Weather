package com.leaf.weather.helper

import android.location.Location
import androidx.lifecycle.LiveData
import com.leaf.weather.model.LocationData

interface LocationHelper {

    fun requestLocationUpdates()

    fun stopLocationUpdates()

    fun getLocationData()
}