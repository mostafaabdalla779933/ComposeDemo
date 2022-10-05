package com.example.composedemo


import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.composedemo.ui.theme.ComposeDemoTheme

@Composable
fun ShowProfileWithConstrainLayoutAndBox(
    context: Context? = null,
    onClick: (Offset) -> Unit = {},
    onDoubleClick: (Offset) -> Unit = {},
    onLongClick: (Offset) -> Unit = {}
) {

    Log.i("main", "ShowProfileWithConstrainLayoutAndBox: ")
    var selected by remember { mutableStateOf(false) }

    val constrainsSet = when(LocalConfiguration.current.orientation){
        Configuration.ORIENTATION_LANDSCAPE ->constrainSetsForLandScape()
        else->constrainSets()
    }

    Card(
        elevation = 20.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 50.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(4.dp, Color.Cyan)
    ) {
        BoxWithConstraints() {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize(),
                constraintSet = constrainsSet
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "add",
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .border(
                            width = 3.dp,
                            color = Color.Red,
                            shape = CircleShape
                        )
                        .layoutId("image")
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = { context?.showText("onClick") },
                                onLongPress = onLongClick,
                                onDoubleTap = onDoubleClick
                            )
                        },
                    contentScale = ContentScale.Crop
                )

                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (selected) Color.Blue else Color.Gray
                    ),
                    onClick = { selected = !selected},
                    modifier = Modifier
                        .layoutId("button")
                ) {
                    Text(text = "add")
                }
            }
        }
    }
}




fun constrainSets(): ConstraintSet {
    Log.i("main", "constrainSets: ")
    return ConstraintSet {
        val image = createRefFor("image")
        val button = createRefFor("button")
        val guideLine = createGuidelineFromEnd(.5f)

        constrain(image) {
            top.linkTo(anchor = parent.top, margin = 15.dp)
            start.linkTo(anchor =parent.start, margin = 15.dp)
            end.linkTo(anchor =parent.end, margin = 15.dp)
            width = Dimension.matchParent
            height = Dimension.ratio("1:1")
        }

        constrain(button) {
            top.linkTo(image.bottom, margin = 10.dp)
            start.linkTo(parent.start)
            end.linkTo(guideLine)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }
    }
}


fun constrainSetsForLandScape(): ConstraintSet {
    Log.i("main", "constrainSetsForLandScape: ")
    return ConstraintSet {
        val image = createRefFor("image")
        val button = createRefFor("button")
        val guideLine = createGuidelineFromEnd(.5f)

        constrain(image) {
            top.linkTo(anchor = parent.top, margin = 15.dp)
            start.linkTo(anchor =parent.start, margin = 15.dp)
            end.linkTo(anchor =parent.end, margin = 15.dp)
            bottom.linkTo(anchor = button.top)
            width = Dimension.ratio("1:1")
            height = Dimension.fillToConstraints
        }

        constrain(button) {
            end.linkTo(parent.end)
            start.linkTo(guideLine)
            bottom.linkTo(parent.bottom, margin = 10.dp)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }
    }
}

@Composable
fun ShowProfileWithConstrainLayout(
    context: Context? = null,
    onClick: (Offset) -> Unit = {},
    onDoubleClick: (Offset) -> Unit = {},
    onLongClick: (Offset) -> Unit = {}
) {

    val selected = remember { mutableStateOf(false) }

    Card(
        elevation = 20.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 50.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(4.dp, Color.Cyan)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {
            val (image, button) = createRefs()
            val guideLine = createGuidelineFromEnd(.5f)
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "add",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1F)
                    .padding(all = 15.dp)
                    .clip(shape = CircleShape)
                    .border(
                        width = 3.dp,
                        color = Color.Red,
                        shape = CircleShape
                    )
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = { context?.showText("onClick") },
                            onLongPress = onLongClick,
                            onDoubleTap = onDoubleClick
                        )
                    }
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop
            )

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selected.value) Color.Blue else Color.Gray
                ),
                onClick = { selected.value = !selected.value },
                modifier = Modifier
                    .constrainAs(button) {
                        top.linkTo(image.bottom, margin = 5.dp)
                        start.linkTo(parent.start)
                        end.linkTo(guideLine)
                        width = Dimension.fillToConstraints
                        // height = Dimension.percent(1F)
                    }
            ) {
                Text(text = "add")
            }
        }
    }
}

@Composable
fun ShowProfile(
    onClick: () -> Unit = {},
    onDoubleClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {

    val selected = remember { mutableStateOf(false) }

    Card(
        elevation = 20.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 50.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(4.dp, Color.Cyan)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {

            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "add",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1F)
                    .padding(all = 15.dp)
                    .clip(shape = CircleShape)
                    .border(
                        width = 3.dp,
                        color = Color.Red,
                        shape = CircleShape
                    )
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = { onClick() },
                            onLongPress = { onLongClick() },
                            onDoubleTap = { onDoubleClick() }
                        )
                    },
                contentScale = ContentScale.Crop
            )

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selected.value) Color.Blue else Color.Gray
                ),
                onClick = { selected.value = !selected.value }) {
                Text(text = "add")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPrevie() {
    ComposeDemoTheme {
        ShowProfileWithConstrainLayout()
    }
}