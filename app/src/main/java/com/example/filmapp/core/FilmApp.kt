package com.example.filmapp.core

import android.app.Application
import com.example.filmapp.filmlistscreen.di.networkModule
import com.example.filmapp.filmlistscreen.di.filmListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FilmApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FilmApp)
            androidLogger()
            modules(
                networkModule,
                filmListModule,
            )
        }
    }
}