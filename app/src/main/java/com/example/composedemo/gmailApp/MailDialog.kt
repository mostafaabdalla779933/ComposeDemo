package com.example.composedemo.gmailApp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController

@Composable
fun DialogMail(openDialog:MutableState<Boolean>){

    Dialog(onDismissRequest = {
        openDialog.value = false
    },
        properties = DialogProperties(dismissOnBackPress = false,dismissOnClickOutside = false)
    ) {

        Column(modifier = Modifier.fillMaxSize(.9F).background(color = Color.Transparent)) {
            Text(
                text = "mostafa ahmed abdulla",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(25.dp))
                    .border(
                        width = 3.dp,
                        color = Color.Red,
                        shape = RoundedCornerShape(25.dp)
                    )
                    .background(color = Color.White)
                    .clickable {
                        openDialog.value = false
                    }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    DialogMail(Any() as MutableState<Boolean>)
}