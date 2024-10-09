package com.example.banquemisrchallenge05.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.banquemisrchallenge05.ui.viewmodles.MoviesViewModel
import com.example.domain.models.Movie

@Composable
fun MoviesScreen(navController: NavHostController, moviesViewModel: MoviesViewModel = viewModel()) {
    val tabs = listOf("Now Playing", "Popular", "Upcoming")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    // get selected Tab
    val nowPlayingMovies by moviesViewModel.nowPlayingMovies.collectAsState()
    val popularMovies by moviesViewModel.popularMovies.collectAsState()
    val upcomingMovies by moviesViewModel.upcomingMovies.collectAsState()

    val moviesLists = listOf(
        nowPlayingMovies,
        popularMovies,
        upcomingMovies
    )

    // Fetch movies for selectedTabIndex
    // selectedTabIndex is the key if changed call LaunchedEffect again according to the index
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

        // Show loading indicator or error message if needed
        if (nowPlayingMovies.isEmpty() && selectedTabIndex == 0) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
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

@Composable
fun MovieList(title: String, movies: List<Movie>, onMovieClick: (Int) -> Unit) {
    Column {
        Text(text = title, style = MaterialTheme.typography.titleMedium)

        if (movies.isEmpty()) {
            Text(text = "No movies available", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyRow {
                items(movies) { movie ->
                    MovieItem(
                        movie = movie,
                        modifier = Modifier.padding(8.dp),
                        onClick = { onMovieClick(movie.id) })
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier = modifier.clickable(onClick = onClick)) {
        Image(
            painter = rememberImagePainter(movie.posterPath),
            contentDescription = movie.title,
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
        )
        Text(text = movie.title, style = MaterialTheme.typography.bodyMedium)
        Text(text = movie.releaseDate, style = MaterialTheme.typography.bodySmall)
    }
}
