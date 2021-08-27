package com.example.filmapp.filmlistscreen.api.repository

import com.example.filmapp.filmlistscreen.domain.ExtractedInfo

interface FilmRepo {
    suspend fun extractFilms(): List<ExtractedInfo>
}