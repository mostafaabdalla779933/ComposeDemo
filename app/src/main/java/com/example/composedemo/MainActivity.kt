package com.example.composedemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.gmailApp.GMailApp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            this.navController = navController
            ComposeDemoTheme(false) {
                GMailApp(navController)
            }
        }


    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navController.handleDeepLink(intent)
    }
}



@Composable
fun ShowText(onClick: () -> Unit) {

    Row(
        Modifier
            .background(color = Color.White)
            .padding(all = 50.dp)
    ) {

        Text(text = "mostafa", color = Color.Yellow)
        Spacer(modifier = Modifier.width(50.dp))
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
        ) {
            Text(text = "button", color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        ShowText {}
    }
}


fun Context.showText(str: String) {
    Toast.makeText(this, str, Toast.LENGTH_LONG).show()
}