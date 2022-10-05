package com.example.composedemo.gmailApp

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material.Text


@Composable
fun Profile(navController: NavController,name:String?,age:Int?) {
    Button(onClick = { }) {
        Text(text = "$name  \n   $age ")
    }
}