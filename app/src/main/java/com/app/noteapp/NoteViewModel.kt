package com.app.noteapp

import androidx.lifecycle.LiveData
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: NoteRepository

    val allNotes : LiveData<List<Note>>

    init {
        val notesDao= NoteRoomDatabase.getDatabase(application,viewModelScope).noteDao()
        mRepository = NoteRepository(notesDao)
        allNotes = mRepository.allNotes
    }

    fun insert(note : Note)= viewModelScope.launch(Dispatchers.IO){
        mRepository.insert(note)
    }
}