package com.example.domain.models

data class MovieDetail(
    val adult: Boolean,
    val id: Int,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val overview: String,
    val title: String,
    val homepage: String
)

