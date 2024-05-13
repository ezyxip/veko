package com.ezyxip.veko.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ezyxip.veko.components.Menuable
import com.ezyxip.veko.data.Identifiable
import com.ezyxip.veko.data.Note

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NoteEditorScreen(
    navigator: (String) -> Unit,
    vm: NoteViewModel = viewModel(),
    noteId: Int
){
    val note = vm.noteList.collectAsState().value.find { e -> e.id == noteId }

    LaunchedEffect(note) {
        if (note == null) {
            navigator("[back]")
        }
    }
    if (note == null) return

    var theme by remember {
        mutableStateOf(note.data.theme)
    }
    var description by remember {
        mutableStateOf(note.data.description)
    }
    vm.tasks.add {
        val note = Identifiable(noteId, Note(theme, description))
        vm.updateNote(note)
    }

    Menuable (
        navigator = navigator,
        actions = {
            IconButton(onClick = {
                vm.tasks.clear()
                vm.deleteNote(noteId)
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    ){
        Column {
            Text(text = theme)
            Text(text = description)
        }
    }
}