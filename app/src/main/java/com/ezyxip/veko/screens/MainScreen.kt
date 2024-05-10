package com.ezyxip.veko.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ezyxip.veko.components.EventCard
import com.ezyxip.veko.components.MainTitle
import com.ezyxip.veko.components.Menuable
import com.ezyxip.veko.components.MiniTitle
import com.ezyxip.veko.components.NoteCard

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigator: (String) -> Unit
){
    Menuable (
        modifier = modifier,
        title = "Главная",
        navigator = navigator
    ){
        Column (
            modifier = modifier
                .padding(25.dp, 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            MainTitle(text = "Ближайшие события")
            Column (
                modifier = modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ){
                MiniTitle(text = "Сегодня")
                EventCard(time = "18-00", title = "Клиент")
                MiniTitle(text = "Вт, 12 мая")
                EventCard(time = "12-30", title = "Тренировка")
                EventCard(time = "16-25", title = "Встреча")
            }
            MainTitle(text = "Последние заметки")
            Column (
                modifier = modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ){
                NoteCard(title = "Список покупок", body = "Lorem ipsum dolor sit amet, consectetur")
                NoteCard(title = "Вдохновение", body = "Lorem ipsum dolor sit amet, consectetur")
            }
        }
    }
}