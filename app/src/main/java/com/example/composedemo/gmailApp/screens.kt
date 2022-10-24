package com.example.composedemo.gmailApp

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.composedemo.R
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

val list = listOf("moatafa", "ahmed" ,
    "abdalla" , "alaa", "Mohsen" ,
    "peter" ,"abdelKareem", "hassan" ,
    "mohamed" ,"Eslam", "eman" ,
    "Dina" ,  "muslim", "remember" ,
    "Column" ,"scrollState", "mutableStateOf" ,
    "HomeScreen" , "text", "LocalContext" ,
    "fillMaxWidth" ,"RoundedCornerShape", "ahmed" )

@Composable
fun HomeScreen(scrollState: ScrollState) {
    val context = LocalContext.current
    var text by rememberSaveable { mutableStateOf("") }
    val searchedList = remember(text, list) {
        list.filter { e -> e.contains(text , true) }
    }
    Column() {

        TextField(
            modifier = Modifier
                .background(color = Color.Transparent)
                .padding(20.dp)
                .fillMaxWidth()
                .background(shape = RoundedCornerShape(10.dp), color = Color.Gray),
            value = text,
            onValueChange = {
                text = it
            },
            label = {
                Text("Label")
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.teal_700))
                .scrollable(scrollState, Orientation.Vertical)
        ) {
            items(searchedList){ str ->
                MailItem(str,context)
            }
        }
    }

}

@Composable
fun MailItem(index:String,context: Context){

    Text(text = "$index",
        Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                Toast
                    .makeText(
                        context,
                        index,
                        Toast.LENGTH_SHORT
                    )
                    .show()
            },
        textAlign = TextAlign.Center
    )
    Divider(modifier = Modifier.padding(10.dp))

}






@Composable
fun AddPostScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {

        Box(){

            Image(
              painter =   painterResource(id = R.drawable.image ),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .blur(radius = 300.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded),
                contentScale = ContentScale.Crop
            )

            Card(elevation = 18.dp,
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(400.dp)
                    .background(Color.Transparent)
                    .align(
                        Alignment.Center
                    )
                ){

                Image(
                    painter =   painterResource(id = R.drawable.image ),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}






@Composable
fun JobScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        MyText()

        Text(
            text = "pager",
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier
                .background(Color.Black)
                .padding(20.dp)
                .clickable { navController.navigate("pager") }
        )

        Text(
            text = "camera",
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier
                .background(Color.Black)
                .padding(20.dp)
                .clickable { navController.navigate("camera_screen") }
        )
    }
}




@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyText(){
    var visible by remember { mutableStateOf(true) }
    Column() {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(
                // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                initialAlpha = 0.4f
            ),
            exit = fadeOut(
                // Overwrites the default animation with tween
                animationSpec = tween(durationMillis = 2000)
            )
        ) {
            // Content that needs to appear/disappear goes here:
            Text(" mostafa ahmed abdalla \n  mostafa ahmed abdalla \n  mostafa ahmed abdalla \n mostafa ahmed abdalla \n")
        }
        Button(onClick = { visible = !visible }) { Text("Toggle") }
    }

}


