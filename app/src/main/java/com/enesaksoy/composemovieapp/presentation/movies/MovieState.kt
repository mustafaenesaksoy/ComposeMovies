package com.enesaksoy.composemovieapp.presentation.movies

import com.enesaksoy.composemovieapp.domain.model.Movie

data class MovieState (
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    val search: String = "batman"
        )