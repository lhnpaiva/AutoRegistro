package com.lhnpaiva.autoregistro.presentation.register

import android.util.Patterns.EMAIL_ADDRESS
import com.lhnpaiva.autoregistro.autentication.FirebaseAuthRepository

class RegisterUseCase(
    private val repository: FirebaseAuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        confirmedPassword: String
    ): Result<Unit> {
        if (email.isBlank() || password.isBlank()) {
            return Result.failure(IllegalArgumentException("Email e senha são obrigatórios"))
        }

        if (!isValidEmail(email)) {
            return Result.failure(IllegalArgumentException("Email inválido"))
        }

        if (password != confirmedPassword) {
            return Result.failure(IllegalArgumentException("As senhas não coincidem"))
        }

        return try {
            repository.register(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return EMAIL_ADDRESS.matcher(email).matches()
    }
}