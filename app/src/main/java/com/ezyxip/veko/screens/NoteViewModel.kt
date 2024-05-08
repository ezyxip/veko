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

    init {
        viewModelScope.launch {
            _noteList.value = noteRepo.all()
        }

        fun addNote(note: Note) {
            viewModelScope.launch {
                noteRepo.add(note)
            }
        }

        fun updateNote(note: Identifiable<Note>) {
            viewModelScope.launch {
                noteRepo.update(note)
            }
        }

        fun deleteNote(id: Int) {
            viewModelScope.launch {
                noteRepo.delete(id)
            }
        }
    }
}