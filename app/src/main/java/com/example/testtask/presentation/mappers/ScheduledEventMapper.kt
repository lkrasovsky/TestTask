package com.example.testtask.presentation.mappers

import com.example.testtask.core.Mapper
import com.example.testtask.domain.models.ScheduledEvent
import com.example.testtask.presentation.models.ScheduledEventUI

class ScheduledEventMapper(
    private val dateMapper: DateMapper
) : Mapper<ScheduledEvent, ScheduledEventUI> {

    override fun map(input: ScheduledEvent): ScheduledEventUI {

        return ScheduledEventUI(
            id = input.id,
            title = input.title,
            subtitle = input.subtitle,
            date = dateMapper.map(input.date),
            imageUrl = input.imageUrl,
        )
    }

}