package com.leaf.domain.usecase.forecast

import com.leaf.domain.model.CurrentWeatherReportDomainModel
import com.leaf.domain.model.WeatherRequest
import com.leaf.domain.utils.DataState
import com.leaf.repository.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetCurrentForecastUseCase {
    suspend fun invoke(weatherRequest: WeatherRequest): Flow<DataState<CurrentWeatherReportDomainModel>>
}