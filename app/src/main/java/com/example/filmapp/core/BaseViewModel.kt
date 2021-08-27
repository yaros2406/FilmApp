package com.example.filmapp.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel<ViewState : BaseViewState>(
    private val initViewState: ViewState
) : ViewModel() {

    private val _viewState: MutableLiveData<ViewState> by lazy { MutableLiveData(initViewState) }
    val viewState: LiveData<ViewState>
        get() = _viewState

    private fun reloadGist(event: Event) =
        viewModelScope.launch {
            val newViewState = reduce(event, viewState.value ?: initViewState)
            if (newViewState != null && newViewState != viewState.value) {
                _viewState.value = newViewState
            }
        }

    protected fun runDataEvent(event: Event) { reloadGist(event) }

    fun runUiEvent(event: Event) { reloadGist(event) }

    abstract suspend fun reduce(event: Event, previousState: ViewState): ViewState? }