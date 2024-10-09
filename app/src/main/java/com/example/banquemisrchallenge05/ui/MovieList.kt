package com.example.banquemisrchallenge05.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.models.Movie

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