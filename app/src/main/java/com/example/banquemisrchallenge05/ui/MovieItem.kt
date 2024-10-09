package com.example.banquemisrchallenge05.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.domain.models.Movie

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