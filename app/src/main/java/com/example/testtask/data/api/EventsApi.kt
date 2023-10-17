package com.example.testtask.data.api

import com.example.testtask.core.retorift.NetworkResult
import com.example.testtask.data.models.EventDTO
import com.example.testtask.data.models.ScheduledEventDTO
import retrofit2.http.GET

interface EventsApi {
    @GET("getEvents")
    suspend fun getEvents(): NetworkResult<List<EventDTO>>

    @GET("getSchedule")
    suspend fun getScheduledEvents(): NetworkResult<List<ScheduledEventDTO>>
}





