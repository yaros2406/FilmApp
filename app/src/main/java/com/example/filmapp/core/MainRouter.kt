package com.example.filmapp.core

import com.example.filmapp.filmlistscreen.ui.model.FilmUi

interface MainRouter {

    fun showFilmInfo(film: FilmUi)
    fun showFilmPlayer(film: FilmUi)
}