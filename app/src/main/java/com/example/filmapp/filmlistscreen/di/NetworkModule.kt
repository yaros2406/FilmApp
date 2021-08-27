package com.example.filmapp.filmlistscreen.di

import com.example.filmapp.filmlistscreen.api.network.NetworkApi
import org.koin.dsl.module

val networkModule = module {

    single {
        NetworkApi().newFilmApiService()
    }
}