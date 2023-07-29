package com.enesaksoy.composemovieapp.domain.use_case.get_movie_detail

import com.enesaksoy.composemovieapp.data.remote.dto.converttoList
import com.enesaksoy.composemovieapp.data.remote.dto.converttoMovieDetail
import com.enesaksoy.composemovieapp.domain.model.Movie
import com.enesaksoy.composemovieapp.domain.model.MovieDetail
import com.enesaksoy.composemovieapp.domain.repository.MovieRepository
import com.enesaksoy.composemovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repo: MovieRepository) {

    fun executeGetMovieDetail(imdbId: String) : Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repo.getMovieDetail(imdbId)
            emit(Resource.Success(movie.converttoMovieDetail()))
        }catch (e: IOException){
            emit(Resource.Error("No Internet Connection!"))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Error!"))
        }
    }
}