package com.example.notesappdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteTable")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var id: Int?,

    @ColumnInfo(name = "note_title")
    var title: String?,

    @ColumnInfo(name = "note_desc")
    var desc: String?,

    @ColumnInfo(name = "fav_note")
    var fav: Boolean?
)
