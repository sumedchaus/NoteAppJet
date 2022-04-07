package com.cs.noteappjet.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.cs.noteappjet.data.NotesDataSource
import com.cs.noteappjet.model.Note

class NoteViewModel : ViewModel() {
    var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note) {
        noteList.add(note)
    }

    fun removeNote(note: Note) {
        noteList.remove(note)
    }

    fun getAllNotes(): List<Note> {
        return noteList
    }
}