package com.example.testtask.presentation.mappers

import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowSystemClock
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant

@RunWith(RobolectricTestRunner::class)
class DateMapperTest {

    private lateinit var test: DateMapper

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    private val defaultDates = listOf(
        dateFormat.parse("2023-10-16T02:44:31"),
        dateFormat.parse("2023-10-15T05:44:31"),
        dateFormat.parse("2023-10-17T05:44:31"),
        dateFormat.parse("2023-10-12T05:44:31"),
    )

    private val defaultMappedDates = listOf(
        "Today, 02:44",
        "Yesterday, 05:44",
        "Tomorrow, 05:44",
        "12.10.2023",
    )


    private lateinit var actualMappedDates: List<String>

    @Before
    fun setup() {
        ShadowSystemClock.advanceBy(Duration.ofSeconds(Instant.parse("2023-10-16T18:35:24.00Z").epochSecond))
        test = DateMapper()
    }

    @Test
    fun `should return mapped dates`() = runTest {
        actualMappedDates = defaultDates.map {
            test.map(it!!)
        }

        checkMappedDates()
    }

    private fun checkMappedDates() {
        println(actualMappedDates)
        Assert.assertEquals(actualMappedDates.size, defaultMappedDates.size)
        Assert.assertTrue(defaultMappedDates.containsAll(actualMappedDates))
    }

}