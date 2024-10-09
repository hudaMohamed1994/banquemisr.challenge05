package com.example.domain.repositories

import com.example.domain.models.Movie
import com.example.domain.models.MovieResponse
import com.example.domain.utils.ResponseWrapper

interface MovieRepository {
    suspend fun getNowPlayingMovies(language: String, page: Int): ResponseWrapper<List<Movie>>
    suspend fun getPopularMovies(language: String, page: Int): ResponseWrapper<List<Movie>>
    suspend fun getUpcomingMovies(language: String, page: Int): ResponseWrapper<List<Movie>>
}