package com.enesaksoy.composemovieapp.presentation.movie_detail

import com.enesaksoy.composemovieapp.domain.model.Movie
import com.enesaksoy.composemovieapp.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = "",
)