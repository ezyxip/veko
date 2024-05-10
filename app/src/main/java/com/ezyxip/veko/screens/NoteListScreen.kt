package com.ezyxip.veko.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ezyxip.veko.components.Menuable
import com.ezyxip.veko.components.NoteCard
import com.ezyxip.veko.data.FileConfiguration
import com.ezyxip.veko.data.Note
import java.io.File

@Composable
fun NoteListScreen(
    modifier: Modifier = Modifier,
    vm: NoteViewModel = viewModel(),
    navigator: (String) -> Unit
){
    val noteList by vm.noteList.collectAsState(initial = emptyList())
    var count = 0
    Menuable (
        title = "Заметки",
        navigator = navigator,
        actions = {
            IconButton(onClick = {
                vm.addNote(Note("New note" + ++count, "description"))
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
            IconButton(onClick = {
                File(FileConfiguration.appDir, "notes").listFiles()
                    ?.forEach { e -> e.deleteRecursively() }
                vm.deleteAllNotes()
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    ) {
        LazyColumn(
            modifier = modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            items(noteList){
                NoteCard(title = it.data.theme, body = it.data.description)
            }
        }
    }
}