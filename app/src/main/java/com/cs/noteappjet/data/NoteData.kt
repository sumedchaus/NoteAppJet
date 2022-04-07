package com.cs.noteappjet.data

import com.cs.noteappjet.model.Note

class NotesDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "A good Day", description = "Hello"),
            Note(title = "A good Day", description = "Hello"),
            Note(title = "A good Day", description = "Hello"),
            Note(title = "A good Day", description = "Hello"),
            Note(title = "A good Day", description = "Hello"),
            Note(title = "A good Day", description = "Hello"),
            Note(title = "A good Day", description = "Hello"),
            Note(title = "A good Day", description = "Hello"),
            Note(title = "A good Day", description = "Hello"),
            Note(title = "A good Day", description = "Hello"),
            Note(title = "A good Day", description = "Hello"),
            Note(title = "A good Day", description = "Hello"),
            Note(title = "A good Day", description = "Hello"),
            Note(title = "A good Day", description = "Hello"),
        )
    }
}