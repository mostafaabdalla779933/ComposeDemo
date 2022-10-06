package com.example.composedemo.camera

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.composedemo.R
import com.example.composedemo.showText


@Composable
fun CameraScreen(){

    val context = LocalContext.current
    var result by remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        result = it
    }

    val startForGallery = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { resul: ActivityResult ->
            if (resul.resultCode == Activity.RESULT_OK) {
                resul.data?.data?.let {
                    result = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                }
            }
        }

    val launcherPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            launcher.launch()
        } else {
           context.showText("something went wrong")
        }
    }
    val launcherPermissionGallery = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { isGranted ->
        if (isGranted.entries.all { e->e.value }) {
            val intent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

            startForGallery.launch(intent)
        } else {
            context.showText("something went wrong")
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
    ) {


        Button(onClick = {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) -> {
                    launcher.launch()
                }
                else -> {
                    launcherPermission.launch(Manifest.permission.CAMERA)
                }
            }
        }) {
           Text(text = "take photo")
        }

        Button(onClick = {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                launcherPermissionGallery.launch(
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                )
            } else {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

                startForGallery.launch(intent)
            }
        }) {
            Text(text = "choose photo")
        }

        result?.let { image ->
            Image(image.asImageBitmap(), null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CameraScreen()

}