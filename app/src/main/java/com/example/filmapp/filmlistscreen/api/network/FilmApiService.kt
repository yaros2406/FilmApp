package com.example.filmapp.filmlistscreen.api.network

import com.example.filmapp.filmlistscreen.api.model.FilmListApi
import retrofit2.http.GET

interface FilmApiService {

    @GET("movies.json")
    suspend fun extractFilms(): FilmListApi
}