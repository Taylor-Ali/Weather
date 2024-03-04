package com.leaf.domain.di

import com.leaf.domain.mapper.PlaceMapper
import com.leaf.domain.mapper.RequestMapper
import com.leaf.domain.usecase.forecast.GetCurrentForecastUseCase
import com.leaf.domain.usecase.forecast.GetCurrentForecastUseCaseImpl
import com.leaf.domain.usecase.forecast.GetWeeklyForecastUseCase
import com.leaf.domain.usecase.forecast.GetWeeklyForecastUseCaseImpl
import com.leaf.domain.usecase.place.DeletePlaceUseCase
import com.leaf.domain.usecase.place.DeletePlaceUseCaseImpl
import com.leaf.domain.usecase.place.GetPlaceUseCase
import com.leaf.domain.usecase.place.GetPlaceUseCaseImpl
import com.leaf.domain.usecase.place.StorePlaceUseCase
import com.leaf.domain.usecase.place.StorePlaceUseCaseImpl
import com.leaf.repository.PlaceRepository
import com.leaf.repository.WeatherForecastRepository
import com.leaf.utils.DateUtils
import com.leaf.utils.RoundingUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun providesRequestMapper(
    ): RequestMapper {
        return RequestMapper
    }

    @Provides
    fun providePlaceMapper(
    ): PlaceMapper {
        return PlaceMapper
    }


    @Provides
    @Singleton
    fun providesGetCurrentForecastUseCase(
        repository: WeatherForecastRepository,
        roundingUtils: RoundingUtils,
        requestMapper: RequestMapper
    ): GetCurrentForecastUseCase {
        return GetCurrentForecastUseCaseImpl(repository, roundingUtils, requestMapper)
    }

    @Provides
    @Singleton
    fun providesGetWeeklyForecastUseCase(
        repository: WeatherForecastRepository,
        roundingUtils: RoundingUtils,
        dateUtils: DateUtils,
        requestMapper: RequestMapper

    ): GetWeeklyForecastUseCase {
        return GetWeeklyForecastUseCaseImpl(repository, dateUtils, roundingUtils, requestMapper)
    }

    @Provides
    @Singleton
    fun providesDeletePlaceUseCase(
        repository: PlaceRepository,
        placeMapper: PlaceMapper
    ): DeletePlaceUseCase {
        return DeletePlaceUseCaseImpl(repository, placeMapper)
    }

    @Provides
    @Singleton
    fun providesGetPlaceUseCase(
        repository: PlaceRepository,
        placeMapper: PlaceMapper
    ): GetPlaceUseCase {
        return GetPlaceUseCaseImpl(repository, placeMapper)
    }

    @Provides
    @Singleton
    fun providesStorePlaceUseCase(
        repository: PlaceRepository,
        placeMapper: PlaceMapper
    ): StorePlaceUseCase {
        return StorePlaceUseCaseImpl(repository, placeMapper)
    }

}