package com.cs.noteappjet.data

import androidx.room.*
import com.cs.noteappjet.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * from notes_tbl")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * from notes_tbl where id =:id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query(value = "DELETE from notes_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)
}
