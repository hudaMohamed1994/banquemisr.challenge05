package com.example.data.repository

import com.example.data.models.toDomainModel
import com.example.data.remote.ApiService
import com.example.domain.models.Movie // Import your domain model
import com.example.domain.repositories.MovieRepository
import com.example.domain.utils.ResponseWrapper

class MovieRepositoryImpl(private val apiService: ApiService) : MovieRepository {

    override suspend fun getNowPlayingMovies(
        language: String,
        page: Int
    ): ResponseWrapper<List<Movie>> { // Change to List<Movie>
        return try {
            val response = apiService.getNowPlayingMovies(language, page)
            if (response.isSuccessful && response.body() != null) {
                // Convert the MovieDTO list to Movie list
                val movies = response.body()!!.results.map { it.toDomainModel() }
                ResponseWrapper.Success(movies)
            } else {
                ResponseWrapper.Error("Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            ResponseWrapper.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }

    override suspend fun getPopularMovies(
        language: String,
        page: Int
    ): ResponseWrapper<List<Movie>> { // Change to List<Movie>
        return try {
            val response = apiService.getPopularMovies(language, page)
            if (response.isSuccessful && response.body() != null) {
                val movies = response.body()!!.results.map { it.toDomainModel() }
                ResponseWrapper.Success(movies)
            } else {
                ResponseWrapper.Error("Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            ResponseWrapper.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }

    override suspend fun getUpcomingMovies(
        language: String,
        page: Int
    ): ResponseWrapper<List<Movie>> { // Change to List<Movie>
        return try {
            val response = apiService.getUpcomingMovies(language, page)
            if (response.isSuccessful && response.body() != null) {
                val movies = response.body()!!.results.map { it.toDomainModel() }
                ResponseWrapper.Success(movies)
            } else {
                ResponseWrapper.Error("Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            ResponseWrapper.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }
}
