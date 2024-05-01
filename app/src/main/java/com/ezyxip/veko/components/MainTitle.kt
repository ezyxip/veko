package com.ezyxip.veko.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ezyxip.veko.interFamily

@Composable
fun MainTitle(
    modifier: Modifier = Modifier,
    text: String
){
    Box(
        modifier = modifier
            .fillMaxWidth()
    ){
        Text(
            text = text,
            fontSize = 24.sp,
            fontFamily = interFamily,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
@Preview
fun MainTitlePreview(){
    MainTitle(text = "Ближайшие события")
}