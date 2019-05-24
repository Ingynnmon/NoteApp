package com.app.noteapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(){

    private val newNoteActivityRequestCode=1
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerview)
        val adapter=NoteListAdapter(this)
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(this)

        noteViewModel=ViewModelProviders.of(this).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this, Observer { notes->
            notes?.let { adapter.setWords(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            val intent=Intent(this@MainActivity,NewNoteActivity::class.java)
            startActivityForResult(intent,newNoteActivityRequestCode)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentdata: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentdata)
        if(requestCode==newNoteActivityRequestCode && resultCode ==Activity.RESULT_OK){
            intentdata?.let { data->
                val note=Note(data.getStringExtra(NewNoteActivity.TITLE), data.getStringExtra(NewNoteActivity.DESCRIPTION))
                noteViewModel.insert(note)
            }
        }else{
            Toast.makeText(
                applicationContext,
                "Note not saved because it is empty.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
