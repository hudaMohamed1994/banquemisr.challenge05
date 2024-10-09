package com.example.banquemisrchallenge05.ui.viewmodles

import com.example.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.MovieDetail
import com.example.domain.utils.ResponseWrapper
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _movieDetailState = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)
    val movieDetailState: MutableStateFlow<MovieDetailState> = _movieDetailState

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            when (val response = movieRepository.getMovieDetails(movieId)) {
                is ResponseWrapper.Success -> {
                    MovieDetailState.Success(response.data)
                }

                is ResponseWrapper.Error -> {
                    MovieDetailState.Error(response.message)

                }
            }
        }

    }

    sealed class MovieDetailState {
        data object Loading : MovieDetailState()
        data class Success(val movie: MovieDetail) : MovieDetailState()
        data class Error(val message: String) : MovieDetailState()
    }
}

