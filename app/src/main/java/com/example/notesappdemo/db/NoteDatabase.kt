package com.example.notesappdemo.db

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun getNoteDao() : NoteDao

    companion object{

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        private const val DB_Name = "note_database.db"

        fun getDatabase(context: Context) : NoteDatabase{

            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                DB_Name
            ).build()

            INSTANCE = instance
            return instance
        }




    }

}