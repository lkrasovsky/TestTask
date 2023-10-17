package com.example.testtask.presentation.mappers

import com.example.testtask.core.retorift.deserializers.ISODate
import com.example.testtask.domain.models.Event
import com.example.testtask.presentation.models.EventUI
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.Date

class EventMapperTest {

    private lateinit var test: EventMapper

    private val defaultEvent = Event(
        "1",
        "1",
        "1",
        ISODate(Date()),
        "1",
        "1"
    )

    private val defaultEventUI = EventUI(
        "1",
        "1",
        "1",
        "1",
        "1",
        "1"
    )

    private lateinit var defaultDateMapper: DateMapper

    private lateinit var actualEventUI: EventUI

    @Before
    fun setup() {
        defaultDateMapper = mockk(relaxed = true)
        test = EventMapper(defaultDateMapper)
    }

    @Test
    fun `should return mapped event`() {
        stubReturnedDate(defaultEventUI.date)

        actualEventUI = test.map(defaultEvent)

        checkMapperReturnedCorrectEvent()
    }

    private fun stubReturnedDate(date: String) {
        coEvery { defaultDateMapper.map(any()) } returns date
    }

    private fun checkMapperReturnedCorrectEvent() {
        Assert.assertTrue(actualEventUI == defaultEventUI)
    }
}