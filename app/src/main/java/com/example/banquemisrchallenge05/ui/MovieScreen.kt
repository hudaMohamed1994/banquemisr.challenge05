package com.example.banquemisrchallenge05.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.banquemisrchallenge05.ui.viewmodles.MoviesViewModel
import com.example.domain.models.Movie
import com.example.banquemisrchallenge05.theme.Banquemisrchallenge05Theme

@Composable
fun MoviesScreen(moviesViewModel: MoviesViewModel = viewModel()) {
    val tabs = listOf("Now Playing", "Popular", "Upcoming")
    // start with the now playing index = 0
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    // Observe the movies lists based on tab selection
    val nowPlayingMovies by moviesViewModel.nowPlayingMovies.collectAsState()
    val popularMovies by moviesViewModel.popularMovies.collectAsState()
    val upcomingMovies by moviesViewModel.upcomingMovies.collectAsState()

    val moviesLists = listOf(
        nowPlayingMovies,
        popularMovies,
        upcomingMovies
    )
     // case it is sideEffect
    // Fetch movies for selectedTabIndex is our {key} to change the api call when change tab
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

        MovieList(title = tabs[selectedTabIndex], movies = moviesLists[selectedTabIndex])
    }
}

@Composable
fun MovieList(title: String, movies: List<Movie>) {
    Column {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        LazyRow {
            items(movies) { movie ->
                MovieItem(movie = movie, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Image(
            painter = rememberImagePainter(movie.posterPath),
            contentDescription = movie.title
        )
        Text(text = movie.title)
        Text(text = movie.releaseDate)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMoviesScreen() {
    Banquemisrchallenge05Theme {
        MoviesScreen()
    }
}
