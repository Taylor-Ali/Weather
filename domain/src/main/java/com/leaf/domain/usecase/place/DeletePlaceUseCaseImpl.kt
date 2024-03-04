package com.leaf.domain.usecase.place

import com.leaf.domain.mapper.PlaceMapper
import com.leaf.domain.model.PlaceDomainModel
import com.leaf.repository.PlaceRepository

class DeletePlaceUseCaseImpl(
    private val placeRepository: PlaceRepository,
    private val placeMapper: PlaceMapper
) : DeletePlaceUseCase {

    override suspend fun invoke(placeDomainModel: PlaceDomainModel) {
        placeMapper.mapToModel(placeDomainModel)?.let { placeRepository.deletePlace(place = it) }
    }
}