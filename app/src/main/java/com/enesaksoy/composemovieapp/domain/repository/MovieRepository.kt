package com.enesaksoy.composemovieapp.domain.repository

import com.enesaksoy.composemovieapp.data.remote.dto.MovieDetailDto
import com.enesaksoy.composemovieapp.data.remote.dto.MoviesDto

interface MovieRepository {

    suspend fun getMovies(search: String): MoviesDto

    suspend fun getMovieDetail(imdbId: String): MovieDetailDto
}