package com.example.domain.usecases

import com.example.domain.models.Movie
import com.example.domain.models.MovieResponse
import com.example.domain.repositories.MovieRepository
import com.example.domain.utils.ResponseWrapper

class GetPopularMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(language: String = "en-US", page: Int = 1): ResponseWrapper<List<Movie>> {
        return movieRepository.getPopularMovies(language, page)
    }
}