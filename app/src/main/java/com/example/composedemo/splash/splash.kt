package com.example.composedemo.splash


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.composedemo.R
import com.example.composedemo.gmailApp.BottomNavItem
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        navController.navigate(BottomNavItem.Home.screen_route) {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
            launchSingleTop = true
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
        }

    }

    Text(text = "splash",
        textAlign = TextAlign.Center,
        fontSize = 40.sp, color = Color.Green,
        modifier = Modifier
            .fillMaxSize()
            .clickable {

            }
            .scale(scale.value)
    )


}

//app:launchSingleTop="true"
//app:popUpTo="@+id/main_nav_graph"
//app:popUpToInclusive="true"