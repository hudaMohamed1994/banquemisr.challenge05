package com.example.domain.usecases

import com.example.domain.models.MovieResponse
import com.example.domain.repositories.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(language: String = "en-US", page: Int = 1): MovieResponse? {
        return movieRepository.getNowPlayingMovies(language, page)
    }
}