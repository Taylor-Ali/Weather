package com.leaf.domain.mapper

import com.leaf.domain.model.PlaceDomainModel
import com.leaf.repository.model.Place

object PlaceMapper:Mapper<PlaceDomainModel,Place> {
    override fun mapFromModel(type: Place?): PlaceDomainModel? {
        return type?.let {
            PlaceDomainModel(
                id = it.id,
                name = it.name,
                latitude = it.latitude,
                longitude = it.longitude,
            )
        }
    }

    override fun mapToModel(type: PlaceDomainModel?): Place? {
        return type?.let {
            Place(
                id = it.id,
                name = it.name,
                latitude = it.latitude,
                longitude = it.longitude,
            )
        }
    }
}