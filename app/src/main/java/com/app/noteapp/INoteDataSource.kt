package com.app.noteapp

import androidx.lifecycle.LiveData

interface INoteDataSource{
    val allNote:LiveData<List<Note>>
    fun insertNote(vararg notes:Note)
    fun updateNote(vararg notes:Note)
    fun deleteNote(notes:Note)
    fun deleteAll(notes: Note)
}