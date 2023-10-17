package com.example.testtask.di.data

import com.example.testtask.core.Mapper
import com.example.testtask.data.api.EventsApi
import com.example.testtask.data.models.EventDTO
import com.example.testtask.data.models.ScheduledEventDTO
import com.example.testtask.di.coroutines.IoDispatcher
import com.example.testtask.domain.models.Event
import com.example.testtask.domain.models.ScheduledEvent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideEventsRepository(
        eventsApi: EventsApi,
        eventMapper: Mapper<EventDTO, Event>,
        scheduledEventMapper: Mapper<ScheduledEventDTO, ScheduledEvent>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): com.example.testtask.domain.repository.EventsRepository {
        return com.example.testtask.data.repositories.EventsRepository(
            eventsApi = eventsApi,
            eventMapper = eventMapper,
            scheduledEventMapper = scheduledEventMapper,
            dispatcher = dispatcher
        )
    }

}