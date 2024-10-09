package com.example.domain.koin

import com.example.domain.usecases.GetPlayingMoviesUseCase
import com.example.domain.usecases.GetPopularMoviesUseCase
import com.example.domain.usecases.GetUpcomingMoviesUseCase
import org.koin.dsl.module

val domainModule = module {
    // Assuming you have a data layer implementation of MovieRepository

    factory { GetPlayingMoviesUseCase(get()) }
    factory { GetPopularMoviesUseCase(get()) }
    factory { GetUpcomingMoviesUseCase(get()) }

}