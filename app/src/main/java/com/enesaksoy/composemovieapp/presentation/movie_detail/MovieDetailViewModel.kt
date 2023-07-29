package com.enesaksoy.composemovieapp.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesaksoy.composemovieapp.domain.use_case.get_movie_detail.GetMovieDetailUseCase
import com.enesaksoy.composemovieapp.util.Constans.IMDB_ID
import com.enesaksoy.composemovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCase: GetMovieDetailUseCase,
    private val savedStateHandle: SavedStateHandle
    ): ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state : State<MovieDetailState>
        get() = _state

    init {
        savedStateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    fun getMovieDetail(imdbId: String){
        useCase.executeGetMovieDetail(imdbId = imdbId).onEach {
            when (it){
                is Resource.Success -> {
                    _state.value = MovieDetailState(movie = it.data)
                }

                is Resource.Error -> {
                    _state.value = MovieDetailState(error = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
    }