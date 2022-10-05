package com.example.composedemo.gmailApp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun GmailDrawerMenu(scaffoldState: ScaffoldState, scope: CoroutineScope, scrollState: ScrollState) {

    val list = listOf(
        DrawerData.AllInbox,
        DrawerData.Header,
        DrawerData.Primary,
        DrawerData.Divider,
        DrawerData.AllInbox,
        DrawerData.Header,
        DrawerData.Primary,
        DrawerData.Divider,
        DrawerData.AllInbox,
        DrawerData.Header,
        DrawerData.Primary,
        DrawerData.Divider,
        DrawerData.AllInbox,
        DrawerData.Header,
        DrawerData.Primary,
        DrawerData.Divider,
        DrawerData.AllInbox,
        DrawerData.Header,
        DrawerData.Primary,
        DrawerData.Divider,
        DrawerData.AllInbox,
        DrawerData.Header,
        DrawerData.Primary,
        DrawerData.Divider,
        DrawerData.Header,
        DrawerData.Primary,
        DrawerData.Divider,
        DrawerData.AllInbox,
        DrawerData.Header,
        DrawerData.Primary,
        DrawerData.Divider,
        DrawerData.AllInbox,
        DrawerData.Header,
        DrawerData.Primary,
        DrawerData.Divider
    )
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Text(
            text = "gmail",
            modifier = Modifier
                .padding(20.dp)
                .clickable {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
                .onGloballyPositioned { coordinate ->
//                    Log.i(
//                        "main",
//                        "GmailDrawerMenu: ${coordinate.positionInParent().y}  ${coordinate.positionInParent().x}   "
//                    )
                } ,
            fontSize = 20.sp, fontWeight = FontWeight.Bold
        )

        Text(text = "click",
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    scope.launch {
                        scrollState.animateScrollTo(700)
                    }
                })

        list.forEach { item ->
            MenuItem(item = item)
        }
    }

}


sealed class DrawerData(
    val icon : ImageVector? = null,
    val title:String? = null,
    val isDivider:Boolean = false,
    val isHeader:Boolean = false
){
    object AllInbox:DrawerData(icon = Icons.Default.Add, title = "all inbox")
    object Primary:DrawerData(icon = Icons.Default.Add, title = "Primary")
    object Divider:DrawerData(isDivider = true)
    object Header:DrawerData(isHeader = true, title = "haeder")
}


@Composable
fun MenuItem(item:DrawerData){

    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(10.dp)) {
        item.icon?.let {
            Image(imageVector = it , contentDescription = "" )
        }
        item.title?.let {
            Text(text = it, modifier = Modifier.weight(2.0f))
        }

        if (item.isDivider){
            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp))
        }
    }

}
