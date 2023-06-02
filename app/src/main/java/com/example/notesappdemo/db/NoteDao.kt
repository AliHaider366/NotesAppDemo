package com.example.notesappdemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note:Note)

    @Update
    suspend fun update(note:Note)

    @Delete
    suspend fun delete(note:Note)

    @Query("Select * from noteTable")
    fun getAllNotes():LiveData<MutableList<Note>>

    @Query("Select * from noteTable where fav_note = :flag")
    fun getAllFavNotes(flag : Boolean):LiveData<MutableList<Note>>

    @Query("SELECT * FROM noteTable ORDER BY note_id DESC LIMIT 1")
    suspend fun getRecentNote():Note?

}