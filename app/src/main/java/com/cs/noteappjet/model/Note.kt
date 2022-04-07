package com.cs.noteappjet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

//@Entity is used for creating database tables
@Entity(tableName = "notes_tbl")
data class Note(
    @PrimaryKey  //for generating key
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "note_title")  // for creating the column
    val title: String,
    @ColumnInfo(name = "note_description")
    val description: String,
    @ColumnInfo(name = "note_entry_date")
    val entryDate: Date = Date.from(Instant.now())
)
