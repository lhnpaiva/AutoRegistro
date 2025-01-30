package com.lhnpaiva.autoregistro.presentation.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<RegisterUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(onEmailChange = {
                user ->
                _uiState.update {
                    it.copy(email = user)
                }
            }, onPasswordChange = { password ->
                _uiState.update {
                    it.copy(password = password)
                }
            }, onConfirmPasswordChange = { password ->
                _uiState.update {
                    it.copy(confirmedPassword = password)
                }
            })
        }
    }

    suspend fun register() {
        val result = registerUseCase(
            uiState.value.email,
            uiState.value.password,
            uiState.value.confirmedPassword
        )
        result.onSuccess {
            _uiEvent.send(RegisterUiEvent.RegisterSuccess)
        }.onFailure { error ->
            _uiState.update {
                it.copy(error = error.message ?: "")
            }
            delay(2000L)
            _uiState.update {
                it.copy(error = "")
            }
        }
    }
}
