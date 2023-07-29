package com.enesaksoy.composemovieapp.data.repository

import com.enesaksoy.composemovieapp.data.remote.MoviesApi
import com.enesaksoy.composemovieapp.data.remote.dto.MovieDetailDto
import com.enesaksoy.composemovieapp.data.remote.dto.MoviesDto
import com.enesaksoy.composemovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MoviesApi): MovieRepository {
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(searchString = search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId = imdbId)
    }
}
