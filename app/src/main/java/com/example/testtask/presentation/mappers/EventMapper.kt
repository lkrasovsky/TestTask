package com.example.testtask.presentation.mappers

import com.example.testtask.core.Mapper
import com.example.testtask.domain.models.Event
import com.example.testtask.presentation.models.EventUI

class EventMapper(
    private val dateMapper: DateMapper
) : Mapper<Event, EventUI> {

    override fun map(input: Event): EventUI {

        return EventUI(
            id = input.id,
            title = input.title,
            subtitle = input.subtitle,
            date = dateMapper.map(input.date),
            imageUrl = input.imageUrl,
            videoUrl = input.videoUrl
        )
    }

}