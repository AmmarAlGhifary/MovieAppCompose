package com.ammar.movieappcompose.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ammar.movieappcompose.R
import com.ammar.movieappcompose.domain.model.moviedetails.Genre
import com.ammar.movieappcompose.presentation.components.ErrorView
import com.ammar.movieappcompose.presentation.viewmodel.MovieDetailsViewModel
import com.ammar.movieappcompose.util.UiState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieId: String,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val detail = remember {
        viewModel.getMovieDetail(movieId.toInt())
        viewModel.movieDetail
    }.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = { Text(text = "Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {
            when {
                detail.value.isLoading -> {
                    Loader()
                }

                detail.value.data != null -> {
                    MovieDetails(detail.value)
                }

                detail.value.error -> {
                    ErrorView { viewModel.getMovieDetail(movieId.toInt()) }
                }
            }
        })
}

@Composable
fun MovieDetails(value: UiState) {
    val movieDetail = value.data
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(10.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieDetail?.image)
                .crossfade(true)
                .error(R.drawable.placeholder)
                .build(),
            contentDescription = "Movie Image",
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column {
                Spacer(Modifier.height(10.dp))
                Text(
                    text = movieDetail?.title ?: "",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = movieDetail?.release_date ?: "",
                    color = Color.Black,
                    fontSize = 14.sp)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp)
                    .align(Alignment.CenterVertically),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = movieDetail?.vote ?: "",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "/10",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(Modifier.height(10.dp))
        LazyRow{
            movieDetail?.genres?.size?.let { total ->
                items(total) { index ->
                    GenreChip(movieDetail.genres[index])
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        Text(text = movieDetail?.overView ?: "",
            color = Color.Black,
            fontSize = 14.sp)
    }
}

@Composable
fun GenreChip(genre: Genre) {
    Surface(
        modifier = Modifier.padding(end = 12.dp),
        shape = RoundedCornerShape(12.dp), color = Color(0xFFDCDCDC)
    ) {
        Text(
            text = genre.name,
            color = Color.DarkGray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(end = 8.dp, start = 8.dp, top = 6.dp, bottom = 6.dp)
        )
    }
}


@Composable
fun Loader() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}
