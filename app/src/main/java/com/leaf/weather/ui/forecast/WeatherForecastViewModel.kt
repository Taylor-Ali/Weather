package com.leaf.weather.ui.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leaf.domain.model.WeatherRequest
import com.leaf.domain.usecase.forecast.GetCurrentForecastUseCase
import com.leaf.domain.usecase.forecast.GetWeeklyForecastUseCase
import com.leaf.domain.utils.DataState
import com.leaf.utils.DateUtils
import com.leaf.weather.R
import com.leaf.weather.model.LocationData
import com.leaf.weather.ui.cities.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val getWeeklyForecastUseCase: GetWeeklyForecastUseCase,
    private val getCurrentForecastUseCase: GetCurrentForecastUseCase,
    private val dateUtils: DateUtils
) : ViewModel() {
    private var location: LocationData? = null

    private val currentWeatherForecastMutableLiveData = MutableLiveData<CurrentWeatherForecast>()
    val currentWeatherForecastLiveData: LiveData<CurrentWeatherForecast> =
        currentWeatherForecastMutableLiveData


    private val weatherForecastMutableLiveData = MutableLiveData<List<WeatherForecastReport>>()
    val weatherForecastLiveData: LiveData<List<WeatherForecastReport>> =
        weatherForecastMutableLiveData


    private val showErrorMessageMutableLiveData = MutableLiveData<Boolean>()
    val showErrorMessageLiveData: LiveData<Boolean> = showErrorMessageMutableLiveData

    private val isLoadingMutableLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = isLoadingMutableLiveData


    private val cityMutableLiveData = MutableLiveData<City>()
    val cityLiveData: LiveData<City> = cityMutableLiveData


    fun addAsCityFavorite(city: City) {
        cityMutableLiveData.postValue(city)
    }

    fun retrieveLocationData(): LocationData? = location

    fun fetchCurrentForecast(locationData: LocationData, refresh: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherRequest = WeatherRequest(
                latitude = locationData.latitude,
                longitude = locationData.longitude,
                cityName = locationData.cityName,
                dateTime = dateUtils.getUnixDate(),
                fetchFromApi = refresh
            )
            getCurrentForecastUseCase.invoke(weatherRequest).collect {
                when (it) {
                    is DataState.Success -> {
                        isLoadingMutableLiveData.postValue(it.isLoading)
                        currentWeatherForecastMutableLiveData.postValue(
                            CurrentWeatherForecast(
                                cityName = it.data?.name.orEmpty(),
                                weatherConditions = it.data?.conditionsDescription.orEmpty(),
                                currentTemp = it.data?.currentTemperature ?: 0,
                                maxTemp = it.data?.maximumTemperature ?: 0,
                                minTemp = it.data?.minimumTemperature ?: 0,
                                weatherConditionTheme = WeatherConditionTheme.valueOf(
                                    it.data?.weatherCondition?.name?.uppercase().orEmpty()
                                )
                            )
                        )
                    }
                    is DataState.Error -> {
                        showErrorMessageMutableLiveData.postValue(true)
                    }
                    is DataState.Loading -> {
                        isLoadingMutableLiveData.postValue(it.isLoading)
                    }
                }
            }
        }
    }

    fun fetchWeatherForecast(locationData: LocationData, refresh: Boolean = false) {
        viewModelScope.launch {
            viewModelScope.launch {
                val weatherRequest = WeatherRequest(
                    latitude = locationData.latitude,
                    longitude = locationData.longitude,
                    cityName = locationData.cityName,
                    dateTime = dateUtils.getUnixDate(),
                    fetchFromApi = refresh
                )
                getWeeklyForecastUseCase.invoke(weatherRequest).collect { response ->
                    when (response) {
                        is DataState.Success -> {
                            isLoadingMutableLiveData.postValue(response.isLoading)
                            weatherForecastMutableLiveData.postValue(response.data?.map {
                                WeatherForecastReport(
                                    dayOfWeek = it.dayOfWeek,
                                    temp = it.temperature,
                                    icon = WeatherConditionTheme.valueOf(it.weatherCondition.name.uppercase()).icon,
                                    measurement = R.string.degree_symbol
                                )
                            })
                        }

                        is DataState.Error -> {
                            showErrorMessageMutableLiveData.postValue(true)
                        }
                        is DataState.Loading -> {
                            isLoadingMutableLiveData.postValue(response.isLoading)
                        }
                    }
                }
            }
        }
    }
}