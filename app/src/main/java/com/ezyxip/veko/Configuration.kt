package com.ezyxip.veko

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.ezyxip.veko.data.FileConfiguration

@Composable
fun configureApp(){
    FileConfiguration.appDir = LocalContext.current.filesDir
}