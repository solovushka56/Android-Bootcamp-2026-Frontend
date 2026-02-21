package ru.sicampus.bootcamp2026.presentation.ui.screens.main.meets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import ru.sicampus.bootcamp2026.domain.entities.meet.Meet
import ru.sicampus.bootcamp2026.domain.usecases.meets.CreateMeetUseCase
import ru.sicampus.bootcamp2026.domain.usecases.meets.GetActiveMeetsUseCase


class MeetsViewModel(
    private val getActiveMeetsUseCase: GetActiveMeetsUseCase,
    private val createMeetUseCase: CreateMeetUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MeetsState>(MeetsState.Loading)
    val state: StateFlow<MeetsState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(capacity = Channel.BUFFERED)
    val events = _events.receiveAsFlow()


    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {

            _state.emit(MeetsState.Loading)
            delay(2000)
            getActiveMeetsUseCase().fold(
                onSuccess = { meets ->
                    _state.emit(
                        MeetsState.Content(
                            createdMeets = meets,
                            invitedMeets = meets
                        )
                    )
                },
                onFailure = { err ->
                    _state.emit(MeetsState.Error(err.message.orEmpty()))
                }
            )
        }
    }

    fun addMeet(meet: Meet) {
        viewModelScope.launch {
            createMeetUseCase(meet).fold(
                onSuccess = {
                    _state.emit(MeetsState.Loading)
                    yield() // Даем Compose шанс увидеть Loading и начать рекомпозицию
                    val updatedMeets = getActiveMeetsUseCase().getOrNull() ?: return@fold
                    _state.emit(MeetsState.Content(updatedMeets, updatedMeets))
                    _events.send(UiEvent.ShowSnackbar("Событие успешно добавлено!"))
                },
                onFailure = {
                    _events.send(UiEvent.ShowSnackbar("Не удалось добавить событие"))
                }
            )
        }
    }
}