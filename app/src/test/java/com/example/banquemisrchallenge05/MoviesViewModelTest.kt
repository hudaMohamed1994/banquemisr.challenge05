package com.example.banquemisrchallenge05

import com.example.domain.models.Movie
import com.example.domain.repositories.MovieRepository
import com.example.domain.utils.ResponseWrapper
import com.example.banquemisrchallenge05.ui.viewmodles.MoviesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesViewModelTest {
    private var movieRepository = mock(MovieRepository::class.java)
    private var moviesViewModel = MoviesViewModel(movieRepository)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `fetchMoviesByTab for now playing should update state with error`() = runTest {
        // Given
        val errorMessage = "Error fetching now playing movies"
        `when`(movieRepository.getNowPlayingMovies("en-US", 1)).thenReturn(
            ResponseWrapper.Error(
                errorMessage
            )
        )

        // When
        moviesViewModel.fetchMoviesByTab(tabIndex = 0, language = "en-US", page = 1)
        advanceUntilIdle()

        // Then
        val error = moviesViewModel.errorMessage.first()
        assertEquals(errorMessage, error)
        assertTrue(moviesViewModel.nowPlayingMovies.first().isEmpty())
        assertFalse(moviesViewModel.isLoading.first())
    }

    @Test
    fun `test success fetchMoviesByTab for popular`() = runTest {
        // Given
        val movies = listOf(
            Movie(
                id = 3, title = "Movie 3",
                releaseDate = "releaseDate",
                posterPath = "posterPath"
            )
        )
        `when`(movieRepository.getPopularMovies("", 1)).thenReturn(
            ResponseWrapper.Success(
                movies
            )
        )
        moviesViewModel.fetchMoviesByTab(tabIndex = 1, language = "en-US", page = 1)
        testDispatcher.scheduler.advanceUntilIdle()
        val popularMovies = moviesViewModel.popularMovies.first()
        assertEquals(movies, popularMovies)
    }

    @Test
    fun `test fetchMoviesByTab for upcoming success`() = runTest {
        val movies = listOf(
            Movie(
                id = 3, title = "Movie 3",
                releaseDate = "releaseDate",
                posterPath = "posterPath"
            )
        )
        `when`(movieRepository.getPopularMovies("", 1)).thenReturn(
            ResponseWrapper.Success(
                movies
            )
        )
        moviesViewModel.fetchMoviesByTab(tabIndex = 2, language = "en-US", page = 1)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        val upcomingMovies = moviesViewModel.upcomingMovies.first()
        assertEquals(movies, upcomingMovies)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
