package com.example.testtask.data.repositories

import com.example.testtask.core.retorift.deserializers.ISODate
import com.example.testtask.data.mappers.ScheduledEventMapper
import com.example.testtask.data.models.ScheduledEventDTO
import com.example.testtask.domain.models.ScheduledEvent
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.Date

class ScheduledEventTest {

    private lateinit var test: ScheduledEventMapper

    private val defaultISODate = ISODate(Date())

    private val defaultScheduledEventDTO = ScheduledEventDTO(
        "1",
        "1",
        "1",
        defaultISODate,
        "1",
    )

    private val defaultScheduledEvent = ScheduledEvent(
        "1",
        "1",
        "1",
        defaultISODate,
        "1",
    )


    private lateinit var actualScheduledEvent: ScheduledEvent

    @Before
    fun setup() {
        test = ScheduledEventMapper()
    }

    @Test
    fun `should return mapped scheduled event`() {
        actualScheduledEvent = test.map(defaultScheduledEventDTO)

        checkMapperReturnedCorrectScheduledEvent()
    }

    private fun checkMapperReturnedCorrectScheduledEvent() {
        Assert.assertTrue(actualScheduledEvent == defaultScheduledEvent)
    }
}