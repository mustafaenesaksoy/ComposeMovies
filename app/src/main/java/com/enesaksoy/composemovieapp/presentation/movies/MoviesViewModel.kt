package com.enesaksoy.composemovieapp.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesaksoy.composemovieapp.domain.use_case.get_movies.GetMovieUseCase
import com.enesaksoy.composemovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val useCase: GetMovieUseCase): ViewModel() {

    private val _state = mutableStateOf<MovieState>(MovieState())
    val state : State<MovieState>
        get() = _state

    private var job : Job? = null

    init {
        getMovies(_state.value.search)
    }
    private fun getMovies(search: String){
        job?.cancel()
        job = useCase.executeGetMovies(search).onEach {
            when (it){
                is Resource.Success -> {
                    _state.value = MovieState(movies = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = MovieState(error = it.message ?: "")
                }

                is Resource.Loading -> {
                    _state.value = MovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MoviesEvent) {
        when (event){
            is MoviesEvent.Search -> {
                getMovies(event.searchString)
            }
        }
    }
}