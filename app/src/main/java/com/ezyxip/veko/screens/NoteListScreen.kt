package com.ezyxip.veko.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ezyxip.veko.components.Menuable

@Composable
fun NoteListScreen(
    modifier: Modifier = Modifier,
    vm: NoteViewModel = viewModel()
){
    val noteList by vm.noteList.collectAsState(initial = emptyList())
    Menuable (
        title = "Заметки",
        actions = { }
    ) {

    }
}