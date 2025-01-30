package com.lhnpaiva.autoregistro.presentation.login

sealed interface LoginUiEvent {
    data object LoginSuccess : LoginUiEvent
}
