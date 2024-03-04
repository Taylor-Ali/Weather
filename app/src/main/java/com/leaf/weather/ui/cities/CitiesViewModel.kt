package com.leaf.weather.ui.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leaf.domain.model.PlaceDomainModel
import com.leaf.domain.usecase.place.DeletePlaceUseCase
import com.leaf.domain.usecase.place.GetPlaceUseCase
import com.leaf.domain.usecase.place.StorePlaceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val getPlaceUseCase: GetPlaceUseCase,
    private val storePlaceUseCase: StorePlaceUseCase,
    private val deletePlaceUseCase: DeletePlaceUseCase

    ) : ViewModel() {
    private val citiesMutableLiveData = MutableLiveData<List<City>>()
    val citiesLiveData: LiveData<List<City>> = citiesMutableLiveData
    private var citiesList = mutableListOf<City>()


    fun addCity(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            storePlaceUseCase.invoke(
                PlaceDomainModel(
                    name = city.name,
                    latitude = city.lat,
                    longitude = city.long,
                )
            )
            citiesList.add(city)

            citiesMutableLiveData.postValue(citiesList)
        }
    }

    fun getAllCity() {
        viewModelScope.launch(Dispatchers.IO) {
//
//            val list = getPlaceUseCase.invoke().collect {
//                City(
//                    id = it?.id,
//                    name = it?.name.orEmpty(),
//                    lat = it?.latitude ?: 0.0,
//                    long = it?.longitude ?: 0.0
//                )
//            }
//            citiesList = list.orEmpty().toMutableList()
//            citiesMutableLiveData.postValue(list.orEmpty())
        }
    }

    fun deleteCity(city: City) {
        viewModelScope.launch {
            citiesList.remove(city)
//            repository.deletePlace(
//                Place(
//                    id = city.id ?: 0,
//                    name = city.name,
//                    latitude = city.lat,
//                    longitude = city.long
//                )
//            )
//            citiesMutableLiveData.postValue(citiesList)
        }
    }
}
