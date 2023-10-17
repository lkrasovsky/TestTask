package com.example.testtask.data.repositories

import com.example.testtask.core.retorift.deserializers.ISODate
import com.example.testtask.data.mappers.EventMapper
import com.example.testtask.data.models.EventDTO
import com.example.testtask.domain.models.Event
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.Date

class EventMapperTest {

    private lateinit var test: EventMapper

    private val defaultISODate = ISODate(Date())

    private val defaultEventDTO = EventDTO(
        "1",
        "1",
        "1",
        defaultISODate,
        "1",
        "1"
    )

    private val defaultEvent = Event(
        "1",
        "1",
        "1",
        defaultISODate,
        "1",
        "1"
    )


    private lateinit var actualEvent: Event

    @Before
    fun setup() {
        test = EventMapper()
    }

    @Test
    fun `should return mapped event`() {
        actualEvent = test.map(defaultEventDTO)

        checkMapperReturnedCorrectEvent()
    }

    private fun checkMapperReturnedCorrectEvent() {
        Assert.assertTrue(actualEvent == defaultEvent)
    }
}