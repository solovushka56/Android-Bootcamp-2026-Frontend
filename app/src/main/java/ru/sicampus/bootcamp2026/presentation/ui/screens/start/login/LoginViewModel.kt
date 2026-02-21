package ru.sicampus.bootcamp2026.presentation.ui.screens.start.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _state = MutableStateFlow<ScreenIntent>(ScreenIntent.Typing)
    val state = _state.asStateFlow()

    var login by mutableStateOf("")
    var password by mutableStateOf("")

    fun authWithCredentials() {
        viewModelScope.launch {
            if (login == "skeeper" && password == "1") {
                _state.emit(ScreenIntent.Send)
            }
        }
    }

    suspend fun onNavigated() {
        _state.emit(ScreenIntent.Typing) // важнейше, чтобы сохранить состояние и избежать багов
    }
}

sealed interface ScreenIntent {
    data object Typing : ScreenIntent
    data object Send : ScreenIntent
    data object Error : ScreenIntent
}