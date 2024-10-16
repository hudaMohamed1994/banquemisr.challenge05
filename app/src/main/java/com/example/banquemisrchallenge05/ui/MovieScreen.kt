package com.example.banquemisrchallenge05.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.banquemisrchallenge05.ui.viewmodles.MoviesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesScreen(navController: NavHostController) {
    val moviesViewModel: MoviesViewModel = koinViewModel()

    val tabs = listOf("Now Playing", "Popular", "Upcoming")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val nowPlayingMovies by moviesViewModel.nowPlayingMovies.collectAsState()
    val popularMovies by moviesViewModel.popularMovies.collectAsState()
    val upcomingMovies by moviesViewModel.upcomingMovies.collectAsState()
    // For Error Handling
    val errorMessage by moviesViewModel.errorMessage.collectAsState()
    val isLoading by moviesViewModel.isLoading.collectAsState()

    val moviesLists = listOf(
        nowPlayingMovies,
        popularMovies,
        upcomingMovies
    )

    // selectedTabIndex is the key to recall the side effect
    LaunchedEffect(selectedTabIndex) {
        moviesViewModel.fetchMoviesByTab(selectedTabIndex)
    }

    Column {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(tab) },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                )
            }
        }

        // Show error message if exists
        errorMessage?.let { message ->
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Show loading indicator to be centered in the screen
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center // Center the content
            ) {
                CircularProgressIndicator()
            }
        } else {
            MovieList(
                title = tabs[selectedTabIndex],
                movies = moviesLists[selectedTabIndex],
                onMovieClick = { movieId ->
                    navController.navigate("movieDetail/$movieId")
                }
            )
        }
    }
}
