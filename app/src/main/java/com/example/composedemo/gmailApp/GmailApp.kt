package com.example.composedemo.gmailApp


import android.content.Context
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.showText


@Composable
fun GMailApp() {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    val homeScrollState = rememberScrollState()

    val bottomAndTopBarState = remember { mutableStateOf(false) }

    val context = LocalContext.current
    Log.i("main", "GMailApp: ${homeScrollState.value}       ${homeScrollState.maxValue}")
    addListener(navController,scaffoldState,context,bottomAndTopBarState)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if(bottomAndTopBarState.value)
            GMailTopBar(scaffoldState = scaffoldState, scope = scope)
        },
        drawerContent = { GmailDrawerMenu(scaffoldState,scope,scrollState) },
        bottomBar = {
            if(bottomAndTopBarState.value)
            BottomNavigation(navController = navController)
        },
        snackbarHost = {
            LaunchedEffect(key1 = null){
                it.showSnackbar("mostafa","hide",SnackbarDuration.Indefinite)
            }
        },
        floatingActionButton = {
            if(bottomAndTopBarState.value)
            FloatingActionButtonGmail(homeScrollState)
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {
                NavigationGraph(navController = navController, homeScrollState = homeScrollState )
            }
        }
    )
}


fun addListener(navController:NavController, scaffoldState: ScaffoldState, context: Context,state : MutableState<Boolean>){
    navController.addOnDestinationChangedListener {_,destination,_->
        when(destination.route){
            "splash" ->{
                state.value =false
            }
            else -> {
                state.value =true
            }
        }
    }
}






class Mostafa{
    var aa = false
     set(value)  {field = value }
     get() = field


    var rr = true
    var dd = ""

}

fun build (builder : Mostafa.() ->String){
    builder.invoke(Mostafa())
}


fun main(){

    build {
        aa = true
        dd = "false"
        rr = false
        ""
    }
}






