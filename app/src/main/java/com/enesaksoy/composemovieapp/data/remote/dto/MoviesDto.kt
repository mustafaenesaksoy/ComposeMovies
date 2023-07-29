package com.enesaksoy.composemovieapp.data.remote.dto


import com.enesaksoy.composemovieapp.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MoviesDto(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val search: List<Search>,
    @SerializedName("totalResults")
    val totalResults: String
)

fun MoviesDto.converttoList(): List<Movie>{
    return search.map { search ->
        Movie(search.imdbID, search.poster, search.title, search.year)
    }
}