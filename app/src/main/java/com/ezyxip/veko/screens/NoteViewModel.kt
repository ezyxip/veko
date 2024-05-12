package com.ezyxip.veko.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezyxip.veko.data.Identifiable
import com.ezyxip.veko.data.Note
import com.ezyxip.veko.data.NoteCRUD
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel: ViewModel() {
    private val _noteList = MutableStateFlow<List<Identifiable<Note>>>(emptyList())
    val noteList: StateFlow<List<Identifiable<Note>>> = _noteList.asStateFlow()
    val noteRepo = NoteCRUD()
    val tasks = mutableListOf<() -> Unit>()

    init {
        viewModelScope.launch {
            _noteList.value = noteRepo.all()
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            val id = noteRepo.add(note)
            _noteList.value = listOf(Identifiable(id, note)) + _noteList.value
        }
    }

    fun exec(){
        tasks.forEach{it()}
        tasks.clear()
    }

    fun updateNote(note: Identifiable<Note>) {
        viewModelScope.launch {
            noteRepo.update(note)
            _noteList.value = _noteList.value
                .map { e -> if (e.id == note.id) note else e }
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            noteRepo.delete(id)
            _noteList.value = _noteList.value.filter { e -> e.id != id }
        }
    }

    fun deleteAllNotes(){
        _noteList.value = listOf()
    }
}