package com.example.testtask.presentation.screens.schedule

import androidx.compose.runtime.Immutable
import com.example.testtask.core.compose.UiEvent
import com.example.testtask.core.compose.UiState
import com.example.testtask.presentation.models.ScheduledEventUI

@Immutable
sealed class ScheduledEventsScreenUiEvent : UiEvent {
    data class ShowData(val items: List<ScheduledEventUI>) : ScheduledEventsScreenUiEvent()
    data class ShowError(val error: Throwable) : ScheduledEventsScreenUiEvent()
}

@Immutable
data class ScheduledEventsScreenState(
    val isLoading: Boolean,
    val data: List<ScheduledEventUI>,
    val error: Throwable? = null
) : UiState {
    companion object {
        fun initial() = ScheduledEventsScreenState(
            isLoading = true,
            data = emptyList(),
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data.size: ${data.size}"
    }
}