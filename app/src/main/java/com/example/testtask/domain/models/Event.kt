package com.example.testtask.domain.models

import com.example.testtask.core.retorift.deserializers.ISODate

data class Event(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: ISODate,
    val imageUrl: String,
    val videoUrl: String,
)
