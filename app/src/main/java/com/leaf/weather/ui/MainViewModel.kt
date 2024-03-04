package com.leaf.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leaf.weather.model.LocationData


class MainViewModel() : ViewModel() {
    private val locationMutableLiveData = MutableLiveData<LocationData>()
    val locationLiveData: LiveData<LocationData> = locationMutableLiveData


    fun getLocationUpdates(location: LocationData) {
        locationMutableLiveData.postValue(location)
    }
}