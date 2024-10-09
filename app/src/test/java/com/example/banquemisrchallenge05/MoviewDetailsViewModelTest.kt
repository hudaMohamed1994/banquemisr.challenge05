package com.example.banquemisrchallenge05

import com.example.banquemisrchallenge05.ui.viewmodles.MovieDetailViewModel
import com.example.domain.models.MovieDetail
import com.example.domain.repositories.MovieRepository
import com.example.domain.utils.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailViewModelTest {

    private val movieRepository: MovieRepository = mock()
    private val testDispatcher = StandardTestDispatcher()
    private var movieDetailViewModel: MovieDetailViewModel = MovieDetailViewModel(movieRepository)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    // runTest is used to execute suspend functions
    @Test
    fun `test success fetchMovieDetails`() = runTest {
        // Mock movieDetail Model
        val movieDetail = MovieDetail(
            id = 1, title = "Test Movie", overview = "Test overview",
            adult = true,
            releaseDate = "",
            revenue = 90,
            runtime = 90,
            homepage = "homepage"
        )
        `when`(movieRepository.getMovieDetails(1)).thenReturn(ResponseWrapper.Success(movieDetail))

        // api call with id 1
        movieDetailViewModel.fetchMovieDetails(1)

        // time to allow coroutine to complete
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = movieDetailViewModel.movieDetailState.first()
        assertTrue(state is MovieDetailViewModel.MovieDetailState.Success)
    }

    @Test
    fun `test fetchMovieDetails Error`() = runTest {
        // Given
        val errorMessage = "mock Error message "
        `when`(movieRepository.getMovieDetails(1)).thenReturn(ResponseWrapper.Error(errorMessage))

        // When
        movieDetailViewModel.fetchMovieDetails(1)

        // Advance time to allow coroutine to complete
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = movieDetailViewModel.movieDetailState.first()
        assertTrue(state is MovieDetailViewModel.MovieDetailState.Error)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
