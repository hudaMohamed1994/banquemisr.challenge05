package com.example.banquemisrchallenge05.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.banquemisrchallenge05.ui.viewmodles.MovieDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailScreen(movieId: Int, navController: NavController) {
    val viewModel: MovieDetailViewModel = koinViewModel()
    val movieDetailState = viewModel.movieDetailState.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
    }

    when (val state = movieDetailState.value) {
        // loading state
        is MovieDetailViewModel.MovieDetailState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center // Center the content
            ) {
                CircularProgressIndicator()
            }
        }
        is MovieDetailViewModel.MovieDetailState.Success -> {
            val movie = state.movie
            // Display movie details
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "Overview: ${movie.overview}")
                Text(text = "Genres: ${movie.title}")
                Text(text = "Runtime: ${movie.runtime} min")

                Spacer(modifier = Modifier.height(16.dp))
                // back to the previous screen
                Button(onClick = { navController.popBackStack() }) {
                    Text("Back to Movie List")
                }
            }
        }
        is MovieDetailViewModel.MovieDetailState.Error -> {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                Button(onClick = { navController.popBackStack() }) {
                    Text("Back to Movie List")
                }
            }
        }
    }
}
