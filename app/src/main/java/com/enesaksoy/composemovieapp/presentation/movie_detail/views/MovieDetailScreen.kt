package com.enesaksoy.composemovieapp.presentation.movie_detail.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.enesaksoy.composemovieapp.presentation.movie_detail.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        state.movie?.let {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = rememberAsyncImagePainter(model = it.poster),
                    contentDescription = it.title,
                    modifier = Modifier
                        .size(300.dp, 300.dp)
                        .clip(RectangleShape)
                        .align(CenterHorizontally)
                        .padding(16.dp)
                )
                
                Text(text = it.title, textAlign = TextAlign.Center,
                modifier = Modifier.padding(14.dp),
                color = Color.White)

                Text(text = it.year, textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White)

                Text(text = it.actors, textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White)

                Text(text = it.country, textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White)

                Text(text = it.director, textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White)

                Text(text = it.imdbRating, textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White)
            }
        }
        if (state.error.isNotEmpty()){
            Text(
                text = state.error,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(
                        Center
                    )
            )
        }

        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Center))
        }
    }
}