package com.example.testtask.presentation.screens.events

import androidx.lifecycle.viewModelScope
import com.example.testtask.core.Mapper
import com.example.testtask.core.compose.Reducer
import com.example.testtask.core.retorift.handleNetworkResult
import com.example.testtask.core.view_model.BaseViewModel
import com.example.testtask.domain.models.Event
import com.example.testtask.domain.repository.EventsRepository
import com.example.testtask.presentation.models.EventUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val repository: EventsRepository,
    private val mapper: Mapper<Event, EventUI>
) : BaseViewModel<EventsScreenState, EventsScreenUiEvent>() {

    private val reducer = EventsReducer(EventsScreenState.initial())

    override val state: StateFlow<EventsScreenState>
        get() = reducer.state

    init {
        fetchEvents()
    }

    fun tryAgain() {
        fetchEvents()
    }

    private fun fetchEvents() {
        viewModelScope.launch {
            repository.getEventsList().handleNetworkResult(
                successBlock = { items ->
                    sendEvent(EventsScreenUiEvent.ShowData(items.map(mapper::map)))
                },
                failureBlock = {
                    sendEvent(EventsScreenUiEvent.ShowError(Exception()))
                }
            )
        }
    }

    private fun sendEvent(event: EventsScreenUiEvent) {
        reducer.sendEvent(event)
    }


    private class EventsReducer(initial: EventsScreenState) :
        Reducer<EventsScreenState, EventsScreenUiEvent>(initial) {
        override fun reduce(
            oldState: EventsScreenState,
            event: EventsScreenUiEvent
        ) {
            when (event) {
                is EventsScreenUiEvent.ShowData -> {
                    setState(oldState.copy(isLoading = false, data = event.items))
                }

                is EventsScreenUiEvent.ShowError -> {
                    setState(oldState.copy(isLoading = false, data = listOf(), error = event.error))
                }
            }
        }
    }

}