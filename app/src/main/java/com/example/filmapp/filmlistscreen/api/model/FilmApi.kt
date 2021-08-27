package com.example.filmapp.filmlistscreen.api.model

import com.google.gson.annotations.SerializedName

class FilmApi(
    @SerializedName("genres")
    val genres: List<GenreApi>,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val posterUrl: String,
    @SerializedName("video")
    val videoUrl: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("vote_count")
    val votersCount: Int
)

class GenreApi(
    @SerializedName("name") val name: String)

class FilmListApi(
    @SerializedName("results") val result: List<FilmApi>)