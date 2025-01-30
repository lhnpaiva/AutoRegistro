package com.lhnpaiva.autoregistro.presentation.register

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val confirmedPassword: String = "",
    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val onConfirmPasswordChange: (String) -> Unit = {},
    val error: String = "",
)