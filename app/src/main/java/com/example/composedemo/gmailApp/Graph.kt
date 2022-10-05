package com.example.composedemo.gmailApp

import android.os.Bundle
import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composedemo.data.Hotel
import com.example.composedemo.gmailApp.notification.DetailScreen
import com.example.composedemo.gmailApp.notification.NotificationScreen
import com.example.composedemo.pagerwithTabs.PagerWithTabs
import com.example.composedemo.posts.NetworkScreen
import com.example.composedemo.splash.SplashScreen
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


@Composable
fun NavigationGraph(navController: NavHostController,homeScrollState:ScrollState) {

    val uri = "https://www.example.com"
    val profile = "Profile"

    NavHost(navController, startDestination = "splash") {


        composable("splash") {
            SplashScreen(navController)
        }

        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(homeScrollState)
        }
        composable(BottomNavItem.MyNetwork.screen_route) {
            NetworkScreen()
        }
        composable(BottomNavItem.AddPost.screen_route) {
            AddPostScreen()
        }
        composable(BottomNavItem.Notification.screen_route) {
            NotificationScreen(navController)
        }
        composable(BottomNavItem.Jobs.screen_route) {
            JobScreen(navController)
        }

        composable(
            "$profile/{name}/{age}",
            arguments = listOf(navArgument("name") { type = NavType.StringType },navArgument("age") { type = NavType.IntType })
        ) { backStackEntry ->
            Profile(navController, backStackEntry.arguments?.getString("name"),backStackEntry.arguments?.getInt("age"))
        }


        composable(
            route = "detail?hotelItem={hotelItem}",
            arguments = mutableListOf(navArgument("hotelItem") { type = HotelNavType })
        ) {
            navController.currentBackStackEntry?.arguments?.getParcelable<Hotel>("hotelItem")?.also {
                DetailScreen(it)
            }
        }

        composable("pager") {
            PagerWithTabs()
        }
    }

}


val HotelNavType: NavType<Hotel> = object : NavType<Hotel>(false) {
    override val name: String
        get() = "hotel"

    override fun get(bundle: Bundle, key: String): Hotel? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Hotel {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: Hotel) {
        bundle.putParcelable(key, value)
    }
}


