package com.example.filmapp.filmlistscreen.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmUi(
    val title: String,
    val overview: String,
    val genres: List<String>,
    val releaseDate: String,
    val posterUrl: String,
    val videoUrl: String,
    val rating: String,
) : Item, Parcelable