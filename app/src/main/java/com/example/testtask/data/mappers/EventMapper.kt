package com.example.testtask.data.mappers

import com.example.testtask.core.Mapper
import com.example.testtask.data.models.EventDTO
import com.example.testtask.domain.models.Event

class EventMapper : Mapper<EventDTO, Event> {
    override fun map(input: EventDTO): Event {
        return Event(
            id = input.id,
            title = input.title,
            subtitle = input.subtitle,
            date = input.date,
            imageUrl = input.imageUrl,
            videoUrl = input.videoUrl
        )
    }
}