package com.example.composedemo.gmailApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.R
import com.example.composedemo.camera.CameraScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun GMailTopBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {

    val openDialog = rememberSaveable {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.padding(10.dp)) {
        Card(
            modifier = Modifier.requiredHeight(50.dp),
            shape = RoundedCornerShape(10.dp), elevation = 10.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {

                if(openDialog.value)
                 DialogMail(openDialog)

                Icon(
                    Icons.Default.Menu, "menu",
                    modifier = Modifier.clickable(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }, onClickLabel = "menu")
                )
                Text(text = "search in mails ", modifier = Modifier.weight(1.0F))
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(shape = CircleShape)
                        .clickable {
                            openDialog.value = true
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GMailTopBar(rememberScaffoldState(), rememberCoroutineScope())

}