package com.ezyxip.veko.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ezyxip.veko.interFamily

@Composable
fun EventCard(
    modifier: Modifier = Modifier,
    time: String,
    title: String
){
    Card (
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                shape = RoundedCornerShape(5.dp),
                elevation = 10.dp,
                clip = true,
                spotColor = Color(0xFFCACACA)
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row (
            modifier = modifier
                .padding(15.dp, 10.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ){
            Text(text = time, fontFamily = interFamily)
            Text(text = title, fontFamily = interFamily)
        }
    }
}

@Composable
@Preview
fun EventCardPreview(){
    Box(modifier = Modifier.padding(10.dp)){
        EventCard(time = "18-00", title = "Встреча")
    }
}