package com.example.composedemo.posts

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedemo.R
import com.example.composedemo.network.PostModel
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NetworkScreen(viewModel: PostsVM = hiltViewModel()) {


    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
    ) {

        HandleState(scrollState,viewModel.statePaging,viewModel )
    }
}

@Composable
fun HandleState(scrollState: ScrollState,statePaging:ScreenState,viewModel: PostsVM) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .scrollable(scrollState, Orientation.Vertical),
        state = listState
    ) {

        items(statePaging.items.size) { i ->
            if (i >= statePaging.items.size - 1 && !statePaging.endReached && !statePaging.isLoading) {
                viewModel.loadNextItems()
            }
            PostItem(statePaging.items[i],i)
        }
        item {
            if (statePaging.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

}

@Composable
fun PostItem(index: PostModel?, position: Int) {

    val context = LocalContext.current

    Row {
        Text(
            text = "${index?.title} $position        ${index?.id}",
            Modifier
                .padding(10.dp)
                .weight(1f)
                .wrapContentHeight()
                .clickable {
                    Toast
                        .makeText(
                            context,
                            index?.title,
                            Toast.LENGTH_SHORT
                        )
                        .show()
                },
            textAlign = TextAlign.Center
        )

        GlideImage(
            imageModel = "https://h5p.org/sites/default/files/h5p/content/1209180/images/file-6113d5f8845dc.jpeg",
            modifier = Modifier
                .width(50.dp)
                .height(50.dp),
            shimmerParams = ShimmerParams(
                baseColor = Color.Blue,
                highlightColor = Color.Cyan,
                durationMillis = 3500,
                dropOff = 0.65f,
                tilt = 20f
            ),
            failure = {
                ShimmerParams(
                    baseColor = Color.Blue,
                    highlightColor = Color.Cyan,
                    durationMillis = 3500,
                    dropOff = 0.65f,
                    tilt = 20f
                )
            }
        )
    }

    Divider(modifier = Modifier.padding(10.dp))

}


