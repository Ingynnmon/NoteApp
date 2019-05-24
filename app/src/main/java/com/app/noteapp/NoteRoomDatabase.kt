package com.app.noteapp

import android.content.Context
import android.os.AsyncTask
import android.os.WorkSource
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.annotation.NonNull
import androidx.annotation.RestrictTo
import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.room.Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao():NoteDAO

    companion object {

        @Volatile private var INSTANCE: NoteRoomDatabase? = null

        fun getDatabase(context: Context,scope: CoroutineScope): NoteRoomDatabase {

            return INSTANCE?: synchronized(this){
                val instance=Room.databaseBuilder(context.applicationContext,
                                                        NoteRoomDatabase::class.java,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }

        private class sRoomDatabaseCallback(private val scope: CoroutineScope):RoomDatabase.Callback(){
            override fun onOpen(db:SupportSQLiteDatabase){
                super.onOpen(db)
                INSTANCE?.let{database ->
                    scope.launch (Dispatchers.IO){
                        populateDatabase(database.noteDao())
                    }
                }
            }
        }
        suspend fun populateDatabase(noteDAO: NoteDAO){
            //for clear at start
            //noteDAO.deleteAll()
            //pre inserted data
            var note=Note("Welcome","Welcome from the note app!")
            noteDAO.insert(note)
            note=Note("Hello","Ingynn")
            noteDAO.insert(note)
        }

    }

}
