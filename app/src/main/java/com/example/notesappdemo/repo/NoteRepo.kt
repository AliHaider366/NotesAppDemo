package com.example.notesappdemo.repo

import androidx.lifecycle.LiveData
import com.example.notesappdemo.model.Note
import com.example.notesappdemo.db.dao.NoteDao

class NoteRepo(private val noteDao: NoteDao) {

    suspend fun insertNote(note: Note){
        noteDao.insert(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.update(note)
    }

    suspend fun deleteNote(note: Note){
        noteDao.delete(note)
    }

    fun getAllNotes(): LiveData<MutableList<Note>> = noteDao.getAllNotes()

    fun getAllFavNotes(): LiveData<MutableList<Note>> = noteDao.getAllFavNotes(true)

    suspend fun getRecentNote(): Note? = noteDao.getRecentNote()

}