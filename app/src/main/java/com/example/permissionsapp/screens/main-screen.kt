package com.example.permissionsapp.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import com.example.permissionsapp.MainActivity
import kotlin.coroutines.coroutineContext
import androidx.compose.material.Surface as Surface

@Composable
fun MainScreen(){
    val myImage: Bitmap = BitmapFactory.decodeResource(
        Resources.getSystem(),
        android.R.drawable.ic_menu_gallery
    )
    val result = rememberSaveable {
        mutableStateOf<Bitmap>(myImage);
    }
    val loadImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()){
        result.value = it
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()){
        isGranted: Boolean ->
        if(isGranted){
            loadImage.launch()
        }
        else{
            ActivityResultContracts.RequestPermission()
        }
    }

    val context = LocalContext.current

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
                    fontSize = 20.sp),
                modifier = Modifier.padding(10.dp)
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                color = Color.Gray)
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
                ){
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
                        when(PackageManager.PERMISSION_GRANTED){
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            ) -> loadImage.launch()
                            else -> launcher.launch(Manifest.permission.CAMERA)
                        }
                    }) {
                    Row{
                        Icon(
                            imageVector = Icons.Default.Camera,
                            contentDescription = "Camera",
                            modifier = Modifier.padding(horizontal = 10.dp))
                        Text(
                            text = "Click a picture",
                            modifier = Modifier.clickable {
                                when(PackageManager.PERMISSION_GRANTED){
                                    ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.CAMERA
                                    ) -> loadImage.launch()
                                    else -> launcher.launch(Manifest.permission.CAMERA)
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
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Submit!")
            }
        }
    }
}


