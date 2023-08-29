package com.ammar.movieappcompose.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ammar.movieappcompose.data.di.ApiService
import com.ammar.movieappcompose.util.uiview.MovieView
import com.ammar.movieappcompose.util.converters.toMovieView
import java.io.IOException

class MoviesDataSource(
    private val api: ApiService,
) : PagingSource<Int, MovieView>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieView> {
        val page = params.key ?: 1
        return try {
            val data = api.getMovies(page)
            LoadResult.Page(
                data = data.movies.map { movie ->
                    movie.toMovieView()
                },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page > 500) null else page + 1
            )
        } catch (t: Throwable) {
            var exception = t
            if (t is IOException) {
                exception = IOException("Please check your internet connection and try again")
            }
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieView>): Int? {
        return state.anchorPosition
    }

}