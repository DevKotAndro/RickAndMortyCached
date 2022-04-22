package com.shoshin.rickandmortycached.di.dispatchers

import com.shoshin.domain.common.dispatchers.DispatchersWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DispatchersModule {
    @Provides
    fun provideDispatchers(): DispatchersWrapper = CoroutinesDispatchersWrapper()
}