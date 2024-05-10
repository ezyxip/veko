package com.ezyxip.veko.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ezyxip.veko.components.Menuable

@Composable
fun NoteEditorScreen(
    navigator: (String) -> Unit,
    vm: NoteViewModel = viewModel(),
    noteId: Int
){
    val note = vm.noteList.collectAsState().value.find { e -> e.id == noteId }
    if(note == null){
        navigator("[back]")
        return
    }
    Menuable (
        navigator = navigator,
        actions = {
            IconButton(onClick = { vm.deleteNote(noteId) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    ){
        Column {
            Text(text = note.data.theme)
            Text(text = note.data.description)
        }
    }
}