package com.example.banquemisrchallenge05.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.banquemisrchallenge05.ui.viewmodles.MovieDetailViewModel

@Composable
fun MovieDetailScreen(movieId: Int, navController: NavController) {
    val viewModel: MovieDetailViewModel = viewModel()
    val movieDetailState = viewModel.movieDetailState.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
    }

    when (val state = movieDetailState.value) {
        is MovieDetailViewModel.MovieDetailState.Loading -> {
            Column(modifier = Modifier.padding(16.dp)) {
                CircularProgressIndicator()
                Text(text = "Loading movie details...", modifier = Modifier.padding(top = 8.dp))
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
