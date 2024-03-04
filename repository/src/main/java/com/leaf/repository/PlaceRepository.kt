package com.leaf.repository

import com.leaf.repository.model.Place

interface PlaceRepository {

    suspend fun addPlace(place: Place)

    suspend fun getAllPlaces(): List<Place?>?

    suspend fun deletePlace(place: Place)
}