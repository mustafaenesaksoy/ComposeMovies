package com.enesaksoy.composemovieapp.data.remote

import com.enesaksoy.composemovieapp.data.remote.dto.MovieDetailDto
import com.enesaksoy.composemovieapp.data.remote.dto.MoviesDto
import com.enesaksoy.composemovieapp.util.Constans.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    //https://omdbapi.com/?apikey=206bbb3a&s=batman
    //https://omdbapi.com/?apikey=206bbb3a&i={imdb_api}
    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MoviesDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MovieDetailDto
}