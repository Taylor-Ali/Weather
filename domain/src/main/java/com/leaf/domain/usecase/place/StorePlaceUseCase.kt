package com.leaf.domain.usecase.place

import com.leaf.domain.model.PlaceDomainModel

interface StorePlaceUseCase {
    suspend fun invoke(placeDomainModel: PlaceDomainModel)
}