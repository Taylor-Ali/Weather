package com.leaf.domain.usecase.place

import com.leaf.domain.model.PlaceDomainModel
import com.leaf.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface GetPlaceUseCase {
    suspend fun invoke() :Flow<DataState<List<PlaceDomainModel>>>

}