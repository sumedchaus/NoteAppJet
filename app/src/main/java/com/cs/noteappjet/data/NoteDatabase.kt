package com.cs.noteappjet.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.cs.noteappjet.model.Note
import com.cs.noteappjet.utils.DateConverter
import com.cs.noteappjet.utils.UUIDConverter


@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase : RoomDatabase() {
// if module is not created then all the values of module is need to be written here
    abstract fun noteDao() : NoteDatabaseDao
}