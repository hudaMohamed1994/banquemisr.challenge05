package com.example.data.koin

import com.example.data.remote.RetrofitInstance
import com.example.data.repository.MovieRepositoryImpl
import com.example.domain.repositories.MovieRepository
import org.koin.dsl.module

val dataModule = module {
    single { RetrofitInstance.getApiService() }
    single<MovieRepository> { MovieRepositoryImpl(get()) }


}