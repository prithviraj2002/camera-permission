package com.example.permissionsapp.screens

import android.Manifest
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen() {
    val cameraPermission = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val myImage: Bitmap = BitmapFactory.decodeResource(
        Resources.getSystem(),
        android.R.drawable.ic_menu_gallery
    )
    val result = remember {
        mutableStateOf<Bitmap>(myImage)
    }
    val loadImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) {
        if (it.toString().isEmpty()) {
            result.value = myImage
        } else {
            result.value = it
        }
    }
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Spacer(
            modifier = Modifier.height(30.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create your profile here!",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(10.dp)
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                color = Color.Gray
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(shape = CircleShape)
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        result.value.asImageBitmap(),
                        contentDescription = "Captured image",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp)
                    )
                }
                IconButton(
                    onClick = {
                        if (cameraPermission.hasPermission) {
                            loadImage.launch()
                        } else if (!cameraPermission.hasPermission) {
                            cameraPermission.launchPermissionRequest()
                        }
                    }) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Camera,
                            contentDescription = "Camera",
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                        Text(
                            text = "Click a picture",
                            modifier = Modifier.clickable {
                                if (cameraPermission.hasPermission) {
                                    loadImage.launch()
                                } else if (!cameraPermission.hasPermission) {
                                    cameraPermission.launchPermissionRequest()
                                }
                            }
                        )
                    }
                }
            }
            OutlinedTextField(
                value = "Enter Email", onValueChange = {},
                modifier = Modifier.padding(10.dp),
                textStyle = TextStyle(color = Color.Gray)
            )
            OutlinedTextField(
                value = "Enter Password", onValueChange = {},
                modifier = Modifier.padding(10.dp),
                textStyle = TextStyle(color = Color.Gray)
            )
            OutlinedTextField(
                value = "Enter Name", onValueChange = {},
                modifier = Modifier.padding(10.dp),
                textStyle = TextStyle(color = Color.Gray)
            )
            OutlinedTextField(
                value = "Enter DOB", onValueChange = {},
                modifier = Modifier.padding(10.dp),
                textStyle = TextStyle(color = Color.Gray)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
            ) {
                Text(text = "Submit!")
            }
        }
    }
}

