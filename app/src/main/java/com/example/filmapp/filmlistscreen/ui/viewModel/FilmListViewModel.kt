package com.example.filmapp.filmlistscreen.ui.viewModel

import com.example.filmapp.core.BaseViewModel
import com.example.filmapp.core.Event
import com.example.filmapp.core.DataEvent
import com.example.filmapp.core.FilmListViewState
import com.example.filmapp.core.STATUS
import com.example.filmapp.core.UiEvent
import com.example.filmapp.filmlistscreen.domain.FilmInteractor

class FilmListViewModel(private val filmInteractor: FilmInteractor) :
    BaseViewModel<FilmListViewState>(FilmListViewState()) {

    init {
        runUiEvent(UiEvent.OnRefreshFilms)
    }

    override suspend fun reduce(
        event: Event,
        previousState: FilmListViewState
    ): FilmListViewState? {
        when (event) {

            is UiEvent.OnRefreshFilms -> {
                runDataEvent(DataEvent.OnLoadFilms)
                filmInteractor.extractFilms().fold(
                    onSuccess = { runDataEvent(DataEvent.OnSuccessFilmRequest(it)) },
                    onFailure = { runDataEvent(DataEvent.OnErrorFilmRequest(it)) }
                )
            }

            is DataEvent.OnLoadFilms -> {
                return previousState.copy(status = STATUS.LOAD)
            }

            is DataEvent.OnSuccessFilmRequest -> {
                return previousState.copy(status = STATUS.CONTENT, filmList = event.films)
            }

            is DataEvent.OnErrorFilmRequest -> {
                return previousState.copy(status = STATUS.ERROR, error = event.error)
            }
        }
        return null
    }
}