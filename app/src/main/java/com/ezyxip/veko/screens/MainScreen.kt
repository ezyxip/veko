package com.ezyxip.veko.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ezyxip.veko.components.EventCard
import com.ezyxip.veko.components.MainTitle
import com.ezyxip.veko.components.Menuable
import com.ezyxip.veko.components.MiniTitle
import com.ezyxip.veko.components.NoteCard

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    vm: NoteViewModel = viewModel(),
    navigator: (String) -> Unit
){
    val notes by vm.noteList.collectAsState(initial = emptyList())

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
                notes.take(3)
                    .forEach { e -> NoteCard(
                        title = e.data.theme,
                        body = e.data.description,
                        navigator = navigator,
                        id = e.id
                    ) }
            }
        }
    }
}