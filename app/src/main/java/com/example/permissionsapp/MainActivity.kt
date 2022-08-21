package com.example.permissionsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.permissionsapp.screens.HomeScreen
import com.example.permissionsapp.screens.MainScreen
import com.example.permissionsapp.ui.theme.PermissionsAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PermissionsAppTheme {
                MainScreen()
            }
        }
    }
}


