package com.example.testtask.di.ui

import com.example.testtask.core.Mapper
import com.example.testtask.domain.models.Event
import com.example.testtask.domain.models.ScheduledEvent
import com.example.testtask.presentation.mappers.DateMapper
import com.example.testtask.presentation.mappers.EventMapper
import com.example.testtask.presentation.mappers.ScheduledEventMapper
import com.example.testtask.presentation.models.EventUI
import com.example.testtask.presentation.models.ScheduledEventUI
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
    fun provideEventUIMapper(dateMapper: DateMapper): Mapper<Event, EventUI> =
        EventMapper(dateMapper)

    @Provides
    @Singleton
    fun provideScheduledEventUIMapper(dateMapper: DateMapper): Mapper<ScheduledEvent, ScheduledEventUI> =
        ScheduledEventMapper(dateMapper)


}