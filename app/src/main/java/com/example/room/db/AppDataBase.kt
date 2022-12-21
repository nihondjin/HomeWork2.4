package com.example.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room.db.dao.NoteDao
import com.example.room.db.entity.NoteModel

//это сам база данных

@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}