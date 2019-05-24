package com.app.noteapp

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class Note(
    @field:PrimaryKey
    @field:ColumnInfo(name = "title")
    val title: String, @param:Nullable @field:Nullable
    @field:ColumnInfo(name = "description")
    val description: String
)