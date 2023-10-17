package com.example.testtask.domain.repository

import com.example.testtask.core.retorift.NetworkResult
import com.example.testtask.domain.models.Event
import com.example.testtask.domain.models.ScheduledEvent

interface EventsRepository {
    suspend fun getEventsList(): NetworkResult<List<Event>>
    suspend fun getScheduledEventsList(): NetworkResult<List<ScheduledEvent>>
}