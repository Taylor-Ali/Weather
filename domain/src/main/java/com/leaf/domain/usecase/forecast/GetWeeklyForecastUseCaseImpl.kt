package com.leaf.domain.usecase.forecast

import com.leaf.domain.mapper.RequestMapper
import com.leaf.domain.model.WeatherCondition
import com.leaf.domain.model.WeatherRequest
import com.leaf.domain.model.WeeklyForecastReportDomainModel
import com.leaf.domain.utils.DataState
import com.leaf.repository.WeatherForecastRepository
import com.leaf.repository.utils.Resource
import com.leaf.utils.DateUtils
import com.leaf.utils.RoundingUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetWeeklyForecastUseCaseImpl(
    private val repository: WeatherForecastRepository,
    private val dateUtils: DateUtils,
    private val roundingUtils: RoundingUtils,
    private val requestMapper: RequestMapper
) : GetWeeklyForecastUseCase {
    override suspend fun invoke(weatherRequest: WeatherRequest): Flow<DataState<List<WeeklyForecastReportDomainModel>>> {
        val forecastRequest = requestMapper.mapToModel(weatherRequest)!!
        return repository.getWeeklyWeatherReport(forecastRequest).map { resource ->
            when (resource) {
                is Resource.Success -> {
                    val list = resource.data?.let { report ->
                        report.list.filter { list ->
                            list?.dateTimeText?.contains(TIME_FILTER)
                                ?: false
                        }.map {
                            WeeklyForecastReportDomainModel(
                                dateUtils.getDayOfWeek(it?.dateTimeText.orEmpty())
                                    .orEmpty(),
                                temperature = roundingUtils.roundValues(
                                    it?.main?.temp ?: 0.0
                                ),
                                weatherCondition = WeatherCondition.valueOf(
                                    it?.weather?.component1()?.main?.uppercase().orEmpty()
                                ),
                                timeOfForecast = it?.dateTime ?: 0

                            )

                        }
                    }.orEmpty()
                    DataState.Success(list)
                }

                is Resource.Error -> DataState.Error(
                    message = resource.error?.message.orEmpty(),
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

    companion object {
        const val TIME_FILTER = "12:00:00"
    }
}