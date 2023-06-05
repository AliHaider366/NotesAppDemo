package com.example.notesappdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesappdemo.model.Note
import com.example.notesappdemo.db.NoteDatabase
import com.example.notesappdemo.repo.NoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var allNotes: LiveData<MutableList<Note>>
    private lateinit var allFavNotes: LiveData<MutableList<Note>>
    private val noteRepo: NoteRepo
    private val _lastRecord = MutableLiveData<Note?>()

    val lastRecord: LiveData<Note?> get() = _lastRecord

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        noteRepo = NoteRepo(dao)
    }

    fun initAllNotes() {
        allNotes = noteRepo.getAllNotes()
    }

    fun initFavNotes() {
        allFavNotes = noteRepo.getAllFavNotes()
    }

    fun loadLastRecord() {
        viewModelScope.launch {
            val record = noteRepo.getRecentNote()
            _lastRecord.value = record
        }
    }

    fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.insertNote(note)
        }
    }

    fun update(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.updateNote(note)
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.deleteNote(note)
        }
    }

    fun getAllNotes(): LiveData<MutableList<Note>> {
        return allNotes
    }

    fun getAllFavNotes(): LiveData<MutableList<Note>> {
        return allFavNotes
    }

}