package com.leaf.repository

import com.leaf.cache.LocalDataSource
import com.leaf.repository.mapper.CityEntityMapper
import com.leaf.repository.model.Place

class PlaceRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val cityEntityMapper: CityEntityMapper
) : PlaceRepository {
    override suspend fun addPlace(place: Place) {
        cityEntityMapper.mapFromModel(place)?.let { localDataSource.storeCity(it) }
    }

    override suspend fun getAllPlaces(): List<Place?>? {
        return localDataSource.fetchAllCities()?.map {
            cityEntityMapper.mapToModel(it)
        }

    }

    override suspend fun deletePlace(place: Place) {
        cityEntityMapper.mapFromModel(place)?.let { localDataSource.deleteCity(it) }
    }
}