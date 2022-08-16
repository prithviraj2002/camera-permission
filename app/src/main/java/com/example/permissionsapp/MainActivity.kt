package com.example.permissionsapp

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.permissionsapp.ui.theme.PermissionsAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PermissionsAppTheme {
                val cameraPermission = rememberPermissionState(permission = android.Manifest.permission.CAMERA)
                val myImage:Bitmap = BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_menu_gallery)
                val result = remember{
                    mutableStateOf<Bitmap>(myImage)
                }
                val loadImage = rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()){
                    result.value = it
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(result.value.asImageBitmap(), contentDescription = "image",
                            modifier = Modifier.width(300.dp).height(500.dp))

                        Button(onClick = {
                            if(cameraPermission.hasPermission){
                                loadImage.launch()
                            }
                            else if(!cameraPermission.hasPermission){
                                cameraPermission.launchPermissionRequest()
                            }
                        }) {
                            Text(text = "Capture Image")
                        }
                        when{
                            cameraPermission.hasPermission -> {
                                Text(text = "Camera permission accepted!")
                            }
                            cameraPermission.shouldShowRationale -> {
                                Text(text = "Camera permission is required to capture images for this app")
                            }
                            !cameraPermission.hasPermission && !cameraPermission.shouldShowRationale -> {
                                Text(text = "Camera permission has been denied, go to settings and enable camera permission")
                            }
                        }
                    }
                }
            }
        }
    }
}

