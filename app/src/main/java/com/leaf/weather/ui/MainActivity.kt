package com.leaf.weather.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.leaf.weather.R
import com.leaf.weather.helper.LocationHelperImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel: MainViewModel by viewModels()
    lateinit var locationHelperImpl: LocationHelperImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.weatherForecast
            )
        )
        locationHelperImpl = LocationHelperImpl(context = this.baseContext)

        requestLocationPermissions()

        locationHelperImpl.locationDataLiveData.observe(this) {
            viewModel.getLocationUpdates(it)
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
    }


    private fun requestLocationPermissions() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    getCurrentLocation()
                }

                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    getCurrentLocation()

                }

                else -> {
                    Toast.makeText(
                        this, getString(R.string.permission_location_denied), Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
        launchLocationRequest(locationPermissionRequest)
    }


    private fun launchLocationRequest(locationPermissionRequest: ActivityResultLauncher<Array<String>>) {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }


    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(
                this, getString(R.string.location_permission_not_granted), Toast.LENGTH_SHORT
            ).show()
        } else {
           locationHelperImpl.getLocationData()

        }

    }


    override fun onStop() {
        locationHelperImpl.stopLocationUpdates()
        super.onStop()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }

    override fun onBackPressed() {
        val navController = this.findNavController(R.id.nav_host_fragment)
        if (navController.currentDestination?.id == R.id.weatherForecast) {
            exitProcess(0)
        }
        super.onBackPressed()

    }
}

