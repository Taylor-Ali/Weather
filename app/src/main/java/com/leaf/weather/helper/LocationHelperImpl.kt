package com.leaf.weather.helper

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.leaf.weather.model.LocationData
import java.util.Locale

@SuppressLint("MissingPermission")
class LocationHelperImpl(context: Context) : LocationHelper {
    private var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var geocoder: Geocoder
    private var locationRequest: LocationRequest
    private val locationDataMutableLiveData = MutableLiveData<LocationData>()
    val locationDataLiveData: LiveData<LocationData> = locationDataMutableLiveData

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        geocoder = Geocoder(context, Locale.getDefault())
        locationRequest = LocationRequest.create()
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
    }

    override fun requestLocationUpdates() {}


    override fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun getLocationData() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    val addressList =
                        geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    val cityName = addressList?.component1()?.adminArea
                    val locationData =
                        LocationData(location.latitude, location.longitude, cityName.orEmpty())
                    locationDataMutableLiveData.postValue(locationData)
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }
}