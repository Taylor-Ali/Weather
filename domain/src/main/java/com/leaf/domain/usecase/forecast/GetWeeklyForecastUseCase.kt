package com.leaf.domain.usecase.forecast

import com.leaf.domain.model.WeatherRequest
import com.leaf.domain.model.WeeklyForecastReportDomainModel
import com.leaf.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface GetWeeklyForecastUseCase {
    suspend operator fun invoke(weatherRequest: WeatherRequest): Flow<DataState<List<WeeklyForecastReportDomainModel>>>

}