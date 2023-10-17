package com.example.testtask.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Events : NavigationItem("events", Icons.Filled.Menu, "Events")
    object Schedule : NavigationItem("schedule", Icons.Filled.AccountCircle, "Schedule")
    object Player : NavigationItem("player/{videoUrl}", Icons.Filled.PlayArrow, "Player")
}