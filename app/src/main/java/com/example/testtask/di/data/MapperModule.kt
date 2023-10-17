package com.example.testtask.di.data

import com.example.testtask.core.Mapper
import com.example.testtask.data.models.EventDTO
import com.example.testtask.data.models.ScheduledEventDTO
import com.example.testtask.domain.models.Event
import com.example.testtask.domain.models.ScheduledEvent
import com.example.testtask.presentation.mappers.DateMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideEventMapper(): Mapper<EventDTO, Event> =
        com.example.testtask.data.mappers.EventMapper()

    @Provides
    @Singleton
    fun provideScheduledEventMapper(): Mapper<ScheduledEventDTO, ScheduledEvent> =
        com.example.testtask.data.mappers.ScheduledEventMapper()

    @Provides
    @Singleton
    fun provideDateMapper(): DateMapper = DateMapper()
}