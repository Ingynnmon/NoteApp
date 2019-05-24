package com.app.noteapp

import android.app.Activity.RESULT_CANCELED
import android.text.TextUtils
import android.content.Intent
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


internal class NewNoteActivity : AppCompatActivity() {

    private var edit_title: EditText? = null
    private var edit_decription: EditText? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        edit_title = findViewById(R.id.edit_title)
        edit_decription = findViewById(R.id.edit_description)


        val saveBtn :Button=findViewById(R.id.button_save)
        saveBtn.setOnClickListener {
            val replyIntent=Intent()
            if(TextUtils.isEmpty(edit_title!!.text) || TextUtils.isEmpty(edit_decription!!.text)) {
                setResult(RESULT_CANCELED, replyIntent)
            }else {
                val title = edit_title!!.text.toString()
                val description = edit_decription!!.text.toString()
                replyIntent.putExtra(TITLE, title)
                replyIntent.putExtra(DESCRIPTION, description)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        val TITLE = "title"
        val DESCRIPTION = "description"
    }
}

