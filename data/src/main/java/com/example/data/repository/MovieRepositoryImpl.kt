package com.example.data.repository

import com.example.data.models.MovieResponse
import com.example.data.remote.ApiService

class MovieRepository(private val apiService: ApiService) {

    suspend fun getNowPlayingMovies(language: String = "en-US", page: Int = 1): MovieResponse? {
        val response = apiService.getNowPlayingMovies(language, page)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getPopularMovies(language: String = "en-US", page: Int = 1): MovieResponse? {
        val response = apiService.getPopularMovies(language, page)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getUpcomingMovies(language: String = "en-US", page: Int = 1): MovieResponse? {
        val response = apiService.getUpcomingMovies(language, page)
        return if (response.isSuccessful) response.body() else null
    }
}
