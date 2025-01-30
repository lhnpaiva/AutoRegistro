package com.lhnpaiva.autoregistro.presentation.register

sealed interface RegisterUiEvent {
    data object RegisterSuccess : RegisterUiEvent
}
