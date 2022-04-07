package com.cs.noteappjet.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs.noteappjet.data.NotesDataSource
import com.cs.noteappjet.model.Note
import com.cs.noteappjet.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private  val repository: NoteRepository): ViewModel() {


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect(){listOfNotes ->
                    if(listOfNotes.isNullOrEmpty()){
                        Log.d("Empty", "Empty list")
                    } else {
                        _noteList.value = listOfNotes
                    }
                }
        }
    }
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow() //to get the state

     fun addNote(note: Note) = viewModelScope.launch {
        repository.addNote(note)
    }
     fun updateNote(note:Note) = viewModelScope.launch {
        repository.updateNote(note)
    }
     fun removeNote(note:Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }



    // mutable state is dificult to use with database
    // var noteList = mutableStateListOf<Note>()
//    init {
//        noteList.addAll(NotesDataSource().loadNotes())
//    }

//    fun addNote(note: Note) {
//        noteList.add(note)
//    }
//
//    fun removeNote(note: Note) {
//        noteList.remove(note)
//    }
//
//    fun getAllNotes(): List<Note> {
//        return noteList
//    }
}