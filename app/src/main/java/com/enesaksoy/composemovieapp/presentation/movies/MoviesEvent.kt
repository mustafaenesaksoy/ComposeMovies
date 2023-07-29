package com.enesaksoy.composemovieapp.presentation.movies

sealed class MoviesEvent {
    data class Search(val searchString: String): MoviesEvent()
}