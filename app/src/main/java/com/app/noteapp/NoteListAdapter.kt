package com.app.noteapp

import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NoteListAdapter internal constructor(context: Context)
    : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mNotes = emptyList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteItemView: TextView = itemView.findViewById(R.id.notetitle)
        val notItemView1: TextView = itemView.findViewById(R.id.notedescription)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = mNotes!![position]
        holder.noteItemView.setText(current.title)
        holder.notItemView1.setText(current.description)
    }

    internal fun setWords(notes: List<Note>) {
        this.mNotes = notes
        notifyDataSetChanged()
    }

    override fun getItemCount()= mNotes.size
}

