package com.app.noteapp

import android.os.AsyncTask
import androidx.lifecycle.LiveData


class NoteRepository(private val noteDAO : NoteDAO) {

    val allNotes: LiveData<List<Note>> = noteDAO.getNote()

    fun insert(note : Note) {
        insertAsyncTask(noteDAO).execute(note)
    }

    class insertAsyncTask constructor(private val mAsyncTaskDao: NoteDAO) :
        AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg params: Note): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

   /* companion object {
        private var mInstance: NoteRepository?=null
        fun getInstance(nNoteDataSource:INoteDataSource):NoteRepository{
            if(mInstance==null)
                mInstance= NoteRepository(nNoteDataSource)
            return mInstance!!
        }
    }*/
}
