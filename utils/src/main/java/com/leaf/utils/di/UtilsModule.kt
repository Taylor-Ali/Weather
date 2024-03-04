package com.leaf.utils.di

import com.leaf.utils.BuildConfigProvider
import com.leaf.utils.DateUtils
import com.leaf.utils.RoundingUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    fun providesBuildConfigProvider(): BuildConfigProvider {
        return BuildConfigProvider
    }

    @Provides
    fun providesDateUtils(): DateUtils {
        return DateUtils
    }

    @Provides
    fun providesRoundingUtils(): RoundingUtils {
        return RoundingUtils
    }

}