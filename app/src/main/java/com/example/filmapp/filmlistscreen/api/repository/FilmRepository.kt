package com.example.filmapp.filmlistscreen.api.repository

import com.example.filmapp.filmlistscreen.api.model.matchFilmList
import com.example.filmapp.filmlistscreen.api.network.FilmApiService
import com.example.filmapp.filmlistscreen.domain.ExtractedInfo

class FilmRepository(private val api: FilmApiService) : FilmRepo {

    override suspend fun extractFilms(): List<ExtractedInfo> =
        api.extractFilms().matchFilmList()
}