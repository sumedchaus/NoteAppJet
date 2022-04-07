package com.cs.noteappjet.data

import androidx.room.*
import com.cs.noteappjet.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    // for showing the list
    @Query("SELECT * from notes_tbl")
    fun getNotes(): Flow<List<Note>>

    //for showing the items in the list by id
    @Query("SELECT * from notes_tbl where id =:id")
    suspend fun getNoteById(id: String): Note

    //onConflict is when a same or duplicate value is present the replace it with the new value
    // for inserting new value
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    //for updating
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    // for deleting full table
    @Query(value = "DELETE from notes_tbl")
    suspend fun deleteAll()

//    for deleting single element in table
    @Delete
    suspend fun deleteNote(note: Note)
}
