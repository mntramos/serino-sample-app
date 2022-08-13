package com.example.serino.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.serino.data.model.DatabaseProduct
import com.example.serino.data.model.StringListTypeConverter

@Database(entities = [DatabaseProduct::class], version = 1, exportSchema = false)
@TypeConverters(StringListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDatabaseDao(): ProductDatabaseDao
}