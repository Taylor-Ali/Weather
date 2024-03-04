package com.leaf.repository.mapper

import com.leaf.cache.database.entities.CityEntity
import com.leaf.repository.model.Place

object CityEntityMapper : Mapper<CityEntity, Place> {
    override fun mapFromModel(type: Place?): CityEntity? {
        return try {
            type?.let {
                CityEntity(
                    id = type.id,
                    name = type.name,
                    latitude = type.latitude,
                    longitude = type.longitude
                )
            }

        } catch (e: Exception) {
            null
        }
    }


    override fun mapToModel(type: CityEntity?): Place? {
        return try {
            type?.let {
                Place(
                    id = type.id ?: 0,
                    name = type.name,
                    latitude = type.latitude ?: 0.0,
                    longitude = type.longitude ?: 0.0
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}