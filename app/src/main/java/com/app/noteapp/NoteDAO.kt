package com.app.noteapp

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDAO {
    @Query("SELECT * from note_table ORDER BY title ASC")
    fun getNote(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg note : Note)

    @Delete()
    fun deleteNote(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAll()

}