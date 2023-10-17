package com.example.testtask.presentation.screens.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testtask.presentation.models.ScheduledEventUI
import com.example.testtask.presentation.screens.common.ContentWithProgress
import com.example.testtask.presentation.screens.common.EventItem
import com.example.testtask.presentation.screens.common.TryAgain

@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.error != null -> TryAgain { viewModel.tryAgain() }
            state.isLoading -> ContentWithProgress()
            state.data.isNotEmpty() -> ScheduleItems(eventsList = state.data)
        }
    }
}

@Composable
fun ScheduleItems(
    eventsList: List<ScheduledEventUI>,
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 4.dp),
        state = listState,

        ) {
        items(eventsList) {
            EventItem(
                title = it.title,
                subtitle = it.subtitle,
                date = it.date,
                imageUrl = it.imageUrl,
            )
        }
    }
}