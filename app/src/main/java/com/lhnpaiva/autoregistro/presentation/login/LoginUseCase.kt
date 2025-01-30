package com.lhnpaiva.autoregistro.presentation.login

import android.util.Patterns.EMAIL_ADDRESS
import com.lhnpaiva.autoregistro.autentication.FirebaseAuthRepository

class LoginUseCase(
    private val repository: FirebaseAuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        if (email.isBlank() || password.isBlank()) {
            return Result.failure(IllegalArgumentException("Email e senha são obrigatórios"))
        }

        if (!isValidEmail(email)) {
            return Result.failure(IllegalArgumentException("Email inválido"))
        }

        return try {
            repository.login(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return EMAIL_ADDRESS.matcher(email).matches()
    }
}