package com.example.notesappdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesappdemo.db.dao.NoteDao
import com.example.notesappdemo.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null
        private const val DB_Name = "note_database.db"

        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DB_Name
                ).build()
                INSTANCE = instance
                instance
            }
        }


    }

}