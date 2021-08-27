package com.example.filmapp.filmlistscreen.domain

import com.example.filmapp.filmlistscreen.api.model.matchFilmUiList
import com.example.filmapp.filmlistscreen.api.repository.FilmRepo
import com.example.filmapp.filmlistscreen.ui.model.FilmUi

class FilmInteractor(private val filmRepo: FilmRepo) {

    suspend fun extractFilms(): Result<List<FilmUi>> {
        return runCatching {
            filmRepo.extractFilms().matchFilmUiList()
        }
    }
}