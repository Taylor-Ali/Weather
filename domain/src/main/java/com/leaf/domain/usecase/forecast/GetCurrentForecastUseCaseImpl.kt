package com.leaf.domain.usecase.forecast

import com.leaf.domain.mapper.RequestMapper
import com.leaf.domain.model.CurrentWeatherReportDomainModel
import com.leaf.domain.model.WeatherCondition
import com.leaf.domain.model.WeatherRequest
import com.leaf.domain.utils.DataState
import com.leaf.repository.WeatherForecastRepository
import com.leaf.repository.utils.Resource
import com.leaf.utils.RoundingUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCurrentForecastUseCaseImpl(
    private val repository: WeatherForecastRepository,
    private val roundingUtils: RoundingUtils,
    private val requestMapper: RequestMapper,

    ) : GetCurrentForecastUseCase {
    override suspend fun invoke(weatherRequest: WeatherRequest): Flow<DataState<CurrentWeatherReportDomainModel>> {
        val forecastRequest = requestMapper.mapToModel(weatherRequest)!!
        return repository.getCurrentWeatherReport(forecastRequest).map {
            when (it) {
                is Resource.Success -> {
                    DataState.Success(
                        CurrentWeatherReportDomainModel(
                            name = it.data?.name.orEmpty(),
                            conditionsDescription = it.data?.weather?.component1()?.main.orEmpty(),
                            currentTemperature = roundingUtils.roundValues(
                                it.data?.main?.temp ?: 0.0
                            ),
                            maximumTemperature = roundingUtils.roundValues(
                                it.data?.main?.tempMax ?: 0.0
                            ),
                            minimumTemperature = roundingUtils.roundValues(
                                it.data?.main?.tempMin ?: 0.0
                            ),
                            weatherCondition = WeatherCondition.valueOf(
                                it.data?.weather?.component1()?.main?.uppercase().orEmpty()
                            )
                        )
                    )
                }

                is Resource.Error -> DataState.Error(
                    message = it.error?.message.orEmpty(),
                    data = null,
                    isLoading = false
                )

                is Resource.Loading -> DataState.Loading(
                    errorMessage = "",
                    data = null,
                    isLoading = false
                )
            }
        }
    }
}

