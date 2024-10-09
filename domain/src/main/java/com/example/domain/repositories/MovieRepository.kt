package com.example.domain.repositories

import com.example.domain.models.Movie
import com.example.domain.models.MovieDetail
import com.example.domain.utils.ResponseWrapper

interface MovieRepository {
    suspend fun getNowPlayingMovies(language: String, page: Int): ResponseWrapper<List<Movie>>
    suspend fun getPopularMovies(language: String, page: Int): ResponseWrapper<List<Movie>>
    suspend fun getUpcomingMovies(language: String, page: Int): ResponseWrapper<List<Movie>>
    suspend fun getMovieDetails(movieId: Int): ResponseWrapper<MovieDetail> // Replace with your actual MovieDetail model

}