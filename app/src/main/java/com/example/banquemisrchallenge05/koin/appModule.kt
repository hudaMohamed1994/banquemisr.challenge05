package com.example.banquemisrchallenge05.koin

import org.koin.dsl.module
import com.example.banquemisrchallenge05.ui.viewmodles.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    viewModel { MoviesViewModel(get()) }
}