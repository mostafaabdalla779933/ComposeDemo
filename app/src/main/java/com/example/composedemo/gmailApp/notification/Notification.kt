package com.example.composedemo.gmailApp.notification

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composedemo.R
import com.example.composedemo.data.Hotel
import com.example.composedemo.gmailApp.BottomNavItem
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun NotificationScreen(navController: NavController,viewModel: HotelViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
    ) {

        Card(elevation = 20.dp,
            backgroundColor = Color.Black,
            modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp)
            .clickable {
                navController.navigate("Profile/mostafa/25") {
                    popUpTo(BottomNavItem.Notification.screen_route)
                    anim {
                        enter = R.anim.slide_in_right
                        exit = R.anim.slide_out_left
                        popEnter = R.anim.slide_in_left
                        popExit = R.anim.slide_out_right
                    }
                }
            }
        ) {
            Text(
                text = "Notification Screen",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
        HandleState(viewModel.stateHotel,navController)
    }
}



@Composable
fun HandleState(stateHotel: HotelState,navController: NavController){
    when(stateHotel){
        is HotelState.Hotels ->{
            ShowHotelsList(stateHotel.postsList,navController)
        }
        is HotelState.Loading ->{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is HotelState.Error ->{
           Text(text = stateHotel.message, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun ShowHotelsList(
    hotels: List<Hotel>,
    navController: NavController
) {
    val state = rememberLazyListState()

    LazyColumn(
        state = state
    ) {
        items(hotels) {
            HotelItem(
                hotel = it,
                onSelectHotel = {
                    navController.navigate("detail?hotelItem=${Uri.encode(Json.encodeToString(it))}")
                }
            )
        }
    }
}


@Composable
fun HotelItem(
    hotel: Hotel,
    onSelectHotel: (Hotel) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onSelectHotel(hotel) })
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier
                .width(100.dp)
                .wrapContentHeight(),
            imageModel = hotel.gallery.firstOrNull() ?: "",
            contentScale = ContentScale.Fit,
            placeHolder = ImageVector.vectorResource(R.drawable.ic_add),
            error = ImageVector.vectorResource(R.drawable.ic_add)
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = hotel.name,
                style = MaterialTheme.typography.h6,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = "price",
                            style = MaterialTheme.typography.overline
                        )
                    }
                    Text(
                        text = hotel.price.toString(),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondary
                    )
                }
                Column {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = "rating",
                            style = MaterialTheme.typography.overline
                        )
                    }
                    Text(
                        text = hotel.userRating.toString(),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondary
                    )
                }
                Row {
                    repeat(hotel.stars) {
                        Icon(Icons.Filled.Star, "", tint = MaterialTheme.colors.secondaryVariant)
                    }
                }
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "${hotel.location.address} - ${hotel.location.city}",
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
    Divider(thickness = 1.dp)
}