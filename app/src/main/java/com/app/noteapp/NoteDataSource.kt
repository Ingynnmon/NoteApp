package com.app.noteapp

import androidx.lifecycle.LiveData

class NoteDataSource(private val noteDao:NoteDAO): INoteDataSource{
    override fun deleteAll(notes: Note) {
        noteDao.deleteAll()
    }

    override val allNote: LiveData<List<Note>>
        get() = noteDao.getNote()

    override fun insertNote(vararg notes: Note) {
        noteDao.insert(*notes)
    }

    override fun updateNote(vararg notes: Note) {
        noteDao.insert(*notes)
    }

    override fun deleteNote(notes: Note) {
        noteDao.deleteNote(notes)
    }
}