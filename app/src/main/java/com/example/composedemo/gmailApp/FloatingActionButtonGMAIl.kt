package com.example.composedemo.gmailApp

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.composedemo.showText


@Composable
fun FloatingActionButtonGmail(scrollState: ScrollState){
    val context = LocalContext.current

    if(scrollState.value > 100){
        ExtendedFloatingActionButton(text = { Text(text = "emails") }, onClick = {
            Log.i("main", "FloatingActionButtonGmail: ${context.toString()}")
        },
            icon = { Icon(imageVector = Icons.Default.Add, contentDescription = "")}
        )
    }else{
        FloatingActionButton(onClick = {
            Log.i("main", "FloatingActionButtonGmail: ${context.toString()}")
            context.showText("FloatingActionButton")
        }){
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }
}