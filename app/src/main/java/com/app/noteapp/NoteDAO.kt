package com.app.noteapp

import androidx.room.Dao
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface NoteDAO {
    @Query("SELECT * from note_table ORDER BY title ASC")
    fun getNote(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note : Note)

    @Query("DELETE FROM note_table")
    fun deleteAll()

}