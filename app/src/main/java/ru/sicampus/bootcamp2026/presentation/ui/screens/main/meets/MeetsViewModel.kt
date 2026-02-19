package ru.sicampus.bootcamp2026.presentation.ui.screens.main.meets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2026.data.repos.MeetsRepository
import ru.sicampus.bootcamp2026.data.source.remote.MeetsDataSource
import ru.sicampus.bootcamp2026.domain.usecases.meets.GetActiveMeetsUseCase
import ru.sicampus.bootcamp2026.presentation.ui.screens.main.create.users.list.MeetsState

class MeetsViewModel : ViewModel() {
    private val _state: MutableStateFlow<MeetsState> =
        MutableStateFlow<MeetsState>(MeetsState.Loading)
    val state = _state.asStateFlow()

    private val getActiveMeetsUseCase = GetActiveMeetsUseCase(
        MeetsRepository(MeetsDataSource()))


    init {
        getData()
    }



    fun getData() {
        viewModelScope.launch {
            _state.emit(MeetsState.Loading)
            getActiveMeetsUseCase().fold(
                onSuccess = { data ->
                    _state.emit(MeetsState.Content(data))
                },
                onFailure = { err ->
                    _state.emit(MeetsState.Error(err.message.orEmpty()))
                }
            )

        }
    }
}