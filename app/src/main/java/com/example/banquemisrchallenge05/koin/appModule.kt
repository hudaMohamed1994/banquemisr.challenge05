package com.example.banquemisrchallenge05.koin

import com.example.banquemisrchallenge05.ui.viewmodles.MovieDetailViewModel
import com.example.banquemisrchallenge05.ui.viewmodles.MoviesViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}