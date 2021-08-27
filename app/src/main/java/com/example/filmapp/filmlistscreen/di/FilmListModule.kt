package com.example.filmapp.filmlistscreen.di

import com.example.filmapp.filmlistscreen.api.repository.FilmRepo
import com.example.filmapp.filmlistscreen.api.repository.FilmRepository
import com.example.filmapp.filmlistscreen.domain.FilmInteractor
import com.example.filmapp.filmlistscreen.ui.viewModel.FilmListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val filmListModule = module {

    single<FilmRepo> {
        FilmRepository(get())
    }

    single {
        FilmInteractor(get())
    }

    viewModel {
        FilmListViewModel(get())
    }
}