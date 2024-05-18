package com.ezyxip.veko.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.ezyxip.veko.components.Menuable

@Composable
fun EventScreen(
    navigator: (String) -> Unit,
){
    Menuable (
        title = "События",
        navigator = navigator,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {

    }
}