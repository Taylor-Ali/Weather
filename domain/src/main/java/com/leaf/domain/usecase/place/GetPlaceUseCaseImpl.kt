package com.leaf.domain.usecase.place

import com.leaf.domain.mapper.PlaceMapper
import com.leaf.domain.model.PlaceDomainModel
import com.leaf.domain.utils.DataState
import com.leaf.repository.PlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetPlaceUseCaseImpl(
    private val placeRepository: PlaceRepository,
    private val placeMapper: PlaceMapper
) : GetPlaceUseCase {
    override suspend fun invoke(): Flow<DataState<List<PlaceDomainModel>>> {
        val list = placeRepository.getAllPlaces()
        DataState.Loading(isLoading = true, data = null, errorMessage = "")
        delay(DELAY)
        DataState.Loading(isLoading = false, data = null, errorMessage = "")
        return flow<DataState<List<PlaceDomainModel>>> {
            DataState.Success(
                list?.map { placeMapper.mapFromModel(it) })
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        const val DELAY = 2000L
    }
}