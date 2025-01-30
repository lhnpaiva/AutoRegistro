package com.lhnpaiva.autoregistro

import android.app.Application
import com.lhnpaiva.autoregistro.di.appModule
import com.lhnpaiva.autoregistro.di.firebaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class AutoRegistroApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AutoRegistroApplication)
            modules(appModule, firebaseModule)
        }
    }
}