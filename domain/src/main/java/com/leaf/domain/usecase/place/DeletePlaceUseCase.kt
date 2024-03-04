package com.leaf.domain.usecase.place

import com.leaf.domain.model.PlaceDomainModel

interface DeletePlaceUseCase {
    suspend fun invoke(placeDomainModel: PlaceDomainModel)
}