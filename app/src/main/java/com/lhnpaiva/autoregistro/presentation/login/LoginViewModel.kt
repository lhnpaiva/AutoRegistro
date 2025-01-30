package com.lhnpaiva.autoregistro.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<LoginUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(onEmailChange = { user ->
                _uiState.update {
                    it.copy(email = user)
                }
            }, onPasswordChange = { password ->
                _uiState.update {
                    it.copy(password = password)
                }
            }, onConfirmPasswordChange = { password ->
                _uiState.update {
                    it.copy(confirmPassword = password)
                }
            })
        }
    }

    suspend fun login() {
        val result = loginUseCase(uiState.value.email, uiState.value.password)
        result.onSuccess {
            _uiEvent.send(LoginUiEvent.LoginSuccess)
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