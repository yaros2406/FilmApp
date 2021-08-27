package com.example.filmapp.filmlistscreen.api.model

import com.example.filmapp.filmlistscreen.domain.ExtractedInfo
import com.example.filmapp.filmlistscreen.ui.model.FilmUi

fun FilmListApi.matchFilmList(): List<ExtractedInfo> =
    this.result.map {
        ExtractedInfo(
            genres = it.genres.matchStringList(),
            title = it.title,
            overview = it.overview,
            releaseDate = it.releaseDate,
            posterUrl = it.posterUrl,
            videoUrl = it.videoUrl,
            rating = it.rating,
            votersCount = it.votersCount
        )
    }

fun List<GenreApi>.matchStringList(): List<String> =
    this.map { it.name }

fun List<ExtractedInfo>.matchFilmUiList(): List<FilmUi> =
    this.map { it.matchFilmUi() }

fun ExtractedInfo.matchFilmUi() =
    FilmUi(
        title = this.title,
        overview = this.overview,
        genres = this.genres,
        releaseDate = this.releaseDate,
        posterUrl = this.posterUrl,
        videoUrl = this.videoUrl,
        rating = this.rating.toString()
    )