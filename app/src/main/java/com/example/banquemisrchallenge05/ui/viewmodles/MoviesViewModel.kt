package com.example.banquemisrchallenge05.ui.viewmodles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Movie
import com.example.domain.repositories.MovieRepository
import com.example.domain.utils.ResponseWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _nowPlayingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val nowPlayingMovies: StateFlow<List<Movie>> = _nowPlayingMovies

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> = _popularMovies

    private val _upcomingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val upcomingMovies: StateFlow<List<Movie>> = _upcomingMovies

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage
// to implement Tabs
    fun fetchMoviesByTab(tabIndex: Int, language: String = "en-US", page: Int = 1) {
        viewModelScope.launch {
            when (tabIndex) {
                0 -> fetchNowPlayingMovies(language, page)
                1 -> fetchPopularMovies(language, page)
                2 -> fetchUpcomingMovies(language, page)
            }
        }
    }

    private suspend fun fetchNowPlayingMovies(language: String, page: Int) {
        when (val response = movieRepository.getNowPlayingMovies(language, page)) {
            is ResponseWrapper.Success -> {
                _nowPlayingMovies.value = response.data
            }
            is ResponseWrapper.Error -> {
                _errorMessage.value = response.message
            }
        }
    }

    private suspend fun fetchPopularMovies(language: String, page: Int) {
        when (val response = movieRepository.getPopularMovies(language, page)) {
            is ResponseWrapper.Success -> {
                _popularMovies.value = response.data
            }
            is ResponseWrapper.Error -> {
                _errorMessage.value = response.message
            }
        }
    }

    private suspend fun fetchUpcomingMovies(language: String, page: Int) {
        when (val response = movieRepository.getUpcomingMovies(language, page)) {
            is ResponseWrapper.Success -> {
                _upcomingMovies.value = response.data
            }
            is ResponseWrapper.Error -> {
                _errorMessage.value = response.message
            }
        }
    }
}
