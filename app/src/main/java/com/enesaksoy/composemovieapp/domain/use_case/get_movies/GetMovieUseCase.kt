package com.enesaksoy.composemovieapp.domain.use_case.get_movies

import com.enesaksoy.composemovieapp.data.remote.dto.converttoList
import com.enesaksoy.composemovieapp.domain.model.Movie
import com.enesaksoy.composemovieapp.domain.repository.MovieRepository
import com.enesaksoy.composemovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieUseCase@Inject constructor(private val repo: MovieRepository) {

    fun executeGetMovies(search: String) : Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repo.getMovies(search)
            if (movieList.response.equals("True")){
                emit(Resource.Success(movieList.converttoList()))
            }else{
                emit(Resource.Error("Movie not found!"))
            }

        }catch (e: IOException){
            emit(Resource.Error("No Internet Connection!"))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Error!"))
        }
    }
}