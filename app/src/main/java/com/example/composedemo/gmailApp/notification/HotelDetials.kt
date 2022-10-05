package com.example.composedemo.gmailApp.notification

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composedemo.R
import com.example.composedemo.data.Hotel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage





@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen(
    hotel: Hotel
) {
    val context = LocalContext.current
    val pagerState = rememberPagerState()
    val scrollState = rememberScrollState()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top
        ) {

            Box(modifier =Modifier.fillMaxWidth()){
                HorizontalPager(
                    modifier = Modifier.fillMaxWidth(),
                    state = pagerState,
                    count = hotel.gallery.size,
                ) { page ->
                    GlideImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                        imageModel = hotel.gallery[page],
                        contentScale = ContentScale.FillBounds,
                        placeHolder = ImageVector.vectorResource(R.drawable.ic_add),
                        error = ImageVector.vectorResource(R.drawable.ic_add)
                    )
                }

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier.padding(16.dp).align(Alignment.BottomCenter),
                )
            }


            Column(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.h5,
                    text = hotel.name
                )
                TextButton(
                    onClick = {
                        val geoUri = "http://maps.google.com/maps?q=loc:${hotel.location.latitude},${hotel.location.longitude}(${hotel.name})"
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(geoUri)))
                    }
                ) {
                    Text(text = "${hotel.location.address} - ${hotel.location.city}")
                }
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
                                text = "phone",
                                style = MaterialTheme.typography.overline
                            )
                        }
                        Text(
                            text = hotel.contact.phoneNumber,
                            style = MaterialTheme.typography.body2
                        )
                    }
                    Column {
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            Text(
                                text = "email",
                                textAlign = TextAlign.Right,
                                style = MaterialTheme.typography.overline
                            )
                        }
                        Text(
                            text = hotel.contact.email,
                            textAlign = TextAlign.Right,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
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
                                text = "check in",
                                style = MaterialTheme.typography.overline
                            )
                        }
                        Text(
                            text = "${hotel.checkIn.from} - ${hotel.checkIn.to}",
                            style = MaterialTheme.typography.body2
                        )
                    }
                    Column {
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            Text(
                                text = "check out",
                                textAlign = TextAlign.Right,
                                style = MaterialTheme.typography.overline
                            )
                        }
                        Text(
                            text = "${hotel.checkIn.from} - ${hotel.checkIn.to}",
                            textAlign = TextAlign.Right,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
    }
}