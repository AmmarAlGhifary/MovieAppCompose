package com.ammar.movieappcompose.data.di

import com.ammar.movieappcompose.domain.repository.MoviesRepository
import com.ammar.movieappcompose.domain.usecase.MovieDetailsUseCase
import com.ammar.movieappcompose.domain.usecase.MoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideMoviesUseCase(moviesRepository: MoviesRepository) =
        MoviesUseCase(moviesRepository)

    @Provides
    @Singleton
    fun provideMovieDetailsUseCase(moviesRepository: MoviesRepository) =
        MovieDetailsUseCase(moviesRepository)
}