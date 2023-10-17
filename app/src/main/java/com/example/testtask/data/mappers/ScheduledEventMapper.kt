package com.example.testtask.data.mappers

import com.example.testtask.core.Mapper
import com.example.testtask.data.models.ScheduledEventDTO
import com.example.testtask.domain.models.ScheduledEvent


class ScheduledEventMapper : Mapper<ScheduledEventDTO, ScheduledEvent> {
    override fun map(input: ScheduledEventDTO): ScheduledEvent {
        return ScheduledEvent(
            id = input.id,
            title = input.title,
            subtitle = input.subtitle,
            date = input.date,
            imageUrl = input.imageUrl,
        )
    }
}