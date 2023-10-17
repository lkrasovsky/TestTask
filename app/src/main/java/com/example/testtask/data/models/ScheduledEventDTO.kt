package com.example.testtask.data.models

import com.example.testtask.core.retorift.deserializers.ISODate

data class ScheduledEventDTO(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: ISODate,
    val imageUrl: String,
)
