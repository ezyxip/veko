package com.ezyxip.veko.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ezyxip.veko.components.Menuable
import com.ezyxip.veko.data.Identifiable
import com.ezyxip.veko.data.Note

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NoteEditorScreen(
    navigator: (String) -> Unit,
    modifier: Modifier = Modifier,
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
        Column (
            modifier = modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            BasicTextField(
                modifier = modifier
                    .fillMaxWidth(),
                value = theme,
                onValueChange = {theme = it},
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            BasicTextField(
                modifier = modifier
                    .fillMaxSize(),
                value = description,
                onValueChange = {description = it},
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 30.sp
                )
            )
        }
    }
}