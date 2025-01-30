package com.lhnpaiva.autoregistro.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lhnpaiva.autoregistro.autentication.FirebaseAuthRepository
import com.lhnpaiva.autoregistro.presentation.login.LoginViewModel
import com.lhnpaiva.autoregistro.presentation.register.RegisterUseCase
import com.lhnpaiva.autoregistro.presentation.register.RegisterViewModel
import com.lhnpaiva.autoregistro.presentation.login.LoginUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::FirebaseAuthRepository)
    singleOf(::RegisterUseCase)
    singleOf(::LoginUseCase)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
}

val firebaseModule = module {
    single {
        Firebase.auth
    }
}