package com.example.testtask.presentation.screens.events

import androidx.compose.runtime.Immutable
import com.example.testtask.core.compose.UiEvent
import com.example.testtask.core.compose.UiState
import com.example.testtask.presentation.models.EventUI

@Immutable
sealed class EventsScreenUiEvent : UiEvent {
    data class ShowData(val items: List<EventUI>) : EventsScreenUiEvent()
    data class ShowError(val error: Throwable) : EventsScreenUiEvent()
}

@Immutable
data class EventsScreenState(
    val isLoading: Boolean,
    val data: List<EventUI>,
    val error: Throwable? = null
) : UiState {
    companion object {
        fun initial() = EventsScreenState(
            isLoading = true,
            data = emptyList(),
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data.size: ${data.size}"
    }
}