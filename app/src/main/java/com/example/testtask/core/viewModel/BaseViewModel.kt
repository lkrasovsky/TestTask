package com.example.testtask.core.view_model

import androidx.lifecycle.ViewModel
import com.example.testtask.core.compose.UiEvent
import com.example.testtask.core.compose.UiState
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<T : UiState, in E : UiEvent> : ViewModel() {

    abstract val state: Flow<T>

}