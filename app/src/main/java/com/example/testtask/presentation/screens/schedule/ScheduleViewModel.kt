package com.example.testtask.presentation.screens.schedule

import androidx.lifecycle.viewModelScope
import com.example.testtask.core.Mapper
import com.example.testtask.core.compose.Reducer
import com.example.testtask.core.retorift.handleNetworkResult
import com.example.testtask.core.view_model.BaseViewModel
import com.example.testtask.domain.models.ScheduledEvent
import com.example.testtask.domain.repository.EventsRepository
import com.example.testtask.presentation.models.ScheduledEventUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val mapper: Mapper<ScheduledEvent, ScheduledEventUI>
) : BaseViewModel<ScheduledEventsScreenState, ScheduledEventsScreenUiEvent>() {

    override val state: StateFlow<ScheduledEventsScreenState>
        get() = reducer.state

    private val reducer = ScheduledEventsReducer(ScheduledEventsScreenState.initial())

    private var job: Job? = null

    init {
        startFetchingScheduledEvents()
    }

    fun tryAgain() {
        startFetchingScheduledEvents()
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    private fun startFetchingScheduledEvents() {
        job?.cancel()
        job = viewModelScope.launch {
            while (isActive) {
                eventsRepository.getScheduledEventsList().handleNetworkResult(
                    successBlock = { items ->
                        sendEvent(ScheduledEventsScreenUiEvent.ShowData(items.map(mapper::map)))
                    },
                    failureBlock = {
                        sendEvent(ScheduledEventsScreenUiEvent.ShowError(Exception()))
                    }
                )
                delay(TimeUnit.SECONDS.toMillis(REFRESH_DURATION_IN_SEC))
            }
        }
    }

    private fun sendEvent(event: ScheduledEventsScreenUiEvent) {
        reducer.sendEvent(event)
    }

    private class ScheduledEventsReducer(initial: ScheduledEventsScreenState) :
        Reducer<ScheduledEventsScreenState, ScheduledEventsScreenUiEvent>(initial) {
        override fun reduce(
            oldState: ScheduledEventsScreenState,
            event: ScheduledEventsScreenUiEvent
        ) {
            when (event) {
                is ScheduledEventsScreenUiEvent.ShowData -> {
                    setState(oldState.copy(isLoading = false, data = event.items))
                }

                is ScheduledEventsScreenUiEvent.ShowError -> {
                    setState(oldState.copy(isLoading = false, data = listOf(), error = event.error))
                }
            }
        }
    }

    companion object {
        private const val REFRESH_DURATION_IN_SEC = 30L
    }
}