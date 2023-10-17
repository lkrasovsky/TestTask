package com.example.testtask.presentation.mappers

import com.example.testtask.core.retorift.deserializers.ISODate
import com.example.testtask.domain.models.ScheduledEvent
import com.example.testtask.presentation.models.ScheduledEventUI
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.Date

class ScheduledEventMapperTest {

    private lateinit var test: ScheduledEventMapper

    private val defaultScheduledEvent = ScheduledEvent(
        "1",
        "1",
        "1",
        ISODate(Date()),
        "1",
    )

    private val defaultScheduledEventUI = ScheduledEventUI(
        "1",
        "1",
        "1",
        "1",
        "1",
    )

    private lateinit var defaultDateMapper: DateMapper

    private lateinit var actualScheduledEventUI: ScheduledEventUI

    @Before
    fun setup() {
        defaultDateMapper = mockk(relaxed = true)
        test = ScheduledEventMapper(defaultDateMapper)
    }

    @Test
    fun `should return mapped ScheduledEvent`() {
        stubReturnedDate(defaultScheduledEventUI.date)

        actualScheduledEventUI = test.map(defaultScheduledEvent)

        checkMapperReturnedCorrectScheduledEvent()
    }

    private fun stubReturnedDate(date: String) {
        coEvery { defaultDateMapper.map(any()) } returns date
    }

    private fun checkMapperReturnedCorrectScheduledEvent() {
        Assert.assertTrue(actualScheduledEventUI == defaultScheduledEventUI)
    }
}