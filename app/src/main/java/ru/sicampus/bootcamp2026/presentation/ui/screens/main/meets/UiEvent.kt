package ru.sicampus.bootcamp2026.presentation.ui.screens.main.meets

import androidx.compose.ui.graphics.vector.ImageVector

sealed interface UiEvent {
    data class ShowSnackbar(
        val message: String,
        val icon: ImageVector? = null,
        val actionLabel: String? = null
    ) : UiEvent
}