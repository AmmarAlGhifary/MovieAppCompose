package com.ammar.movieappcompose.data.di

import com.ammar.movieappcompose.data.repository.MovieRepositoryImpl
import com.ammar.movieappcompose.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRepository(repositoryImpl : MovieRepositoryImpl) : MoviesRepository
}