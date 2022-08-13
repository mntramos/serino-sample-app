package com.example.serino.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.serino.data.model.DomainProduct

@Database(entities = [DomainProduct::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDatabaseDao(): ProductDatabaseDao
}