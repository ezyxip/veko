package com.ezyxip.veko.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ezyxip.veko.interFamily

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    id: Int = 1,
    navigator: (String) -> Unit = {}
){
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp)
            .shadow(
                shape = RoundedCornerShape(5.dp),
                elevation = 10.dp,
                clip = true,
                spotColor = Color(0xFFCACACA)
            )
            .clickable { navigator("/note_edit/$id") },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column (
            modifier = modifier
                .padding(15.dp, 10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){
            Text(text = title, fontSize = 18.sp, fontFamily = interFamily, fontWeight = FontWeight.Medium)
            Text(text = body, fontSize = 16.sp, fontFamily = interFamily, fontWeight = FontWeight.Light, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
@Preview
fun NoteCardPreview(){
    Box(modifier = Modifier.padding(10.dp)){
        val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Integer facilisis condimentum dui quis consectetur. " +
                "Nunc tincidunt feugiat justo, non venenatis ipsum accumsan a"
        NoteCard(title = "Список покупок", body = text)
    }
}