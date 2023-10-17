package com.example.testtask.data.repositories

import com.example.testtask.core.Mapper
import com.example.testtask.core.retorift.NetworkResult
import com.example.testtask.core.retorift.map
import com.example.testtask.data.api.EventsApi
import com.example.testtask.data.models.EventDTO
import com.example.testtask.data.models.ScheduledEventDTO
import com.example.testtask.domain.models.Event
import com.example.testtask.domain.models.ScheduledEvent
import com.example.testtask.domain.repository.EventsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class EventsRepository(
    private val eventsApi: EventsApi,
    private val eventMapper: Mapper<EventDTO, Event>,
    private val scheduledEventMapper: Mapper<ScheduledEventDTO, ScheduledEvent>,
    private val dispatcher: CoroutineDispatcher
) : EventsRepository {

    override suspend fun getEventsList(): NetworkResult<List<Event>> = withContext(dispatcher) {
        eventsApi
            .getEvents()
            .map { list -> list.sortedBy { item -> item.date } }
            .map { list -> list.map(eventMapper::map) }
    }

    override suspend fun getScheduledEventsList(): NetworkResult<List<ScheduledEvent>> =
        withContext(dispatcher) {
            eventsApi
                .getScheduledEvents()
                .map { list -> list.sortedBy { item -> item.date } }
                .map { list -> list.map(scheduledEventMapper::map) }
        }

}