package com.lhnpaiva.autoregistro.autentication

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository(
    private val auth: FirebaseAuth
) {
    suspend fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .await()
    }

    suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .await()
    }

    suspend fun getCurrentUser() {
        auth.currentUser != null
    }
}