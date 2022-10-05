package com.example.composedemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composedemo.ui.theme.ComposeDemoTheme

@Composable
fun StateExample (){

    var text by remember { mutableStateOf("") }

    var textPrint  by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = Color.LightGray)) {
        TextField(modifier = Modifier
            .background(color = Color.Black)
            .fillMaxWidth(),value = text, onValueChange = {
            text = it
        })
        Text(text = textPrint)

        Button(onClick = {
            textPrint = text
        }) {
            Text(text = "print")
        }
    }




}



@Preview(showBackground = true)
@Composable
fun DefaultPreviewS() {
    ComposeDemoTheme {
        StateExample ()
    }
}