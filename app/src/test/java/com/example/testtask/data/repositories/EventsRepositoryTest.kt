package com.example.testtask.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.invoices.baseTest.coroutines.CoroutineTestRule
import com.example.testtask.base.deepEquals
import com.example.testtask.core.Mapper
import com.example.testtask.core.retorift.NetworkResult
import com.example.testtask.core.retorift.asSuccess
import com.example.testtask.core.retorift.deserializers.ISODate
import com.example.testtask.data.api.EventsApi
import com.example.testtask.data.mappers.EventMapper
import com.example.testtask.data.mappers.ScheduledEventMapper
import com.example.testtask.data.models.EventDTO
import com.example.testtask.data.models.ScheduledEventDTO
import com.example.testtask.domain.models.Event
import com.example.testtask.domain.models.ScheduledEvent
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowSystemClock
import java.time.Duration
import java.time.Instant
import java.util.Date

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class EventsRepositoryTest {

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var test: EventsRepository
    private lateinit var defaultEventsApi: EventsApi
    private lateinit var defaultEventMapper: Mapper<EventDTO, Event>
    private lateinit var defaultScheduledEventMapper: Mapper<ScheduledEventDTO, ScheduledEvent>

    private val defaultFirstISODate = ISODate(Date(1))
    private val defaultSecondISODate = ISODate(Date(2))
    private val defaultThirdISODate = ISODate(Date(3))

    private val defaultEventListApiResponse = listOf(
        EventDTO(
            id = "2",
            title = "2",
            subtitle = "2",
            date = defaultSecondISODate,
            imageUrl = "2",
            videoUrl = "2"
        ),
        EventDTO(
            id = "1",
            title = "1",
            subtitle = "1",
            date = defaultFirstISODate,
            imageUrl = "1",
            videoUrl = "1"
        ),
        EventDTO(
            id = "3",
            title = "3",
            subtitle = "3",
            date = defaultThirdISODate,
            imageUrl = "3",
            videoUrl = "3"
        ),
    )

    private val defaultScheduledListApiResponse = listOf(
        ScheduledEventDTO(
            id = "2",
            title = "2",
            subtitle = "2",
            date = defaultSecondISODate,
            imageUrl = "2"
        ),
        ScheduledEventDTO(
            id = "1",
            title = "1",
            subtitle = "1",
            date = defaultFirstISODate,
            imageUrl = "1"
        ),
        ScheduledEventDTO(
            id = "3",
            title = "3",
            subtitle = "3",
            date = defaultThirdISODate,
            imageUrl = "3"
        ),
    )

    private val defaultEventList = listOf(
        Event(
            id = "1",
            title = "1",
            subtitle = "1",
            date = defaultFirstISODate,
            imageUrl = "1",
            videoUrl = "1"
        ),
        Event(
            id = "2",
            title = "2",
            subtitle = "2",
            date = defaultSecondISODate,
            imageUrl = "2",
            videoUrl = "2"
        ),
        Event(
            id = "3",
            title = "3",
            subtitle = "3",
            date = defaultThirdISODate,
            imageUrl = "3",
            videoUrl = "3"
        ),
    )

    private val defaultScheduledListEvents = listOf(
        ScheduledEvent(
            id = "1",
            title = "1",
            subtitle = "1",
            date = defaultFirstISODate,
            imageUrl = "1"
        ),
        ScheduledEvent(
            id = "2",
            title = "2",
            subtitle = "2",
            date = defaultSecondISODate,
            imageUrl = "2"
        ),
        ScheduledEvent(
            id = "3",
            title = "3",
            subtitle = "3",
            date = defaultThirdISODate,
            imageUrl = "3"
        ),
    )

    private lateinit var actualEventsList: List<Event>
    private lateinit var actualScheduledItemsListEvent: List<ScheduledEvent>

    @Before
    fun setup() {
        ShadowSystemClock.advanceBy(Duration.ofSeconds(Instant.now().epochSecond))
        defaultEventsApi = mockk(relaxed = true)
        defaultEventMapper = EventMapper()
        defaultScheduledEventMapper = ScheduledEventMapper()
        test = EventsRepository(
            defaultEventsApi,
            defaultEventMapper,
            defaultScheduledEventMapper,
            UnconfinedTestDispatcher()
        )
    }


    @Test
    fun `should return mapped list of events`() = runTest {
        stubReturnedEventsResponse(defaultEventListApiResponse)

        actualEventsList = test.getEventsList().asSuccess().value

        checkRepositoryReturnedCorrectEventsList()
        checkRepositoryReturnedSortedEventsList()
    }

    @Test
    fun `should return mapped list of scheduled events`() = runTest {
        stubReturnedScheduledListResponse(defaultScheduledListApiResponse)

        actualScheduledItemsListEvent = test.getScheduledEventsList().asSuccess().value

        checkRepositoryReturnedCorrectScheduledItemsList()
        checkRepositoryReturnedSortedScheduledEventsList()
    }

    private fun stubReturnedEventsResponse(list: List<EventDTO>) {
        coEvery { defaultEventsApi.getEvents() } returns NetworkResult.Success.Value(list)
    }

    private fun stubReturnedScheduledListResponse(list: List<ScheduledEventDTO>) {
        coEvery { defaultEventsApi.getScheduledEvents() } returns NetworkResult.Success.Value(list)
    }

    private fun checkRepositoryReturnedCorrectEventsList() {
        Assert.assertEquals(actualEventsList.size, defaultEventList.size)
        Assert.assertTrue(defaultEventList.containsAll(actualEventsList))
    }

    private fun checkRepositoryReturnedSortedEventsList() {
        actualEventsList.deepEquals(defaultEventList)
    }

    private fun checkRepositoryReturnedCorrectScheduledItemsList() {
        Assert.assertEquals(actualScheduledItemsListEvent.size, defaultScheduledListEvents.size)
        Assert.assertTrue(defaultScheduledListEvents.containsAll(actualScheduledItemsListEvent))
    }

    private fun checkRepositoryReturnedSortedScheduledEventsList() {
        actualScheduledItemsListEvent.deepEquals(defaultScheduledListEvents)
    }


}