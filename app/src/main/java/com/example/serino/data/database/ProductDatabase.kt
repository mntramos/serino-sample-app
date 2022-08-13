package com.example.serino.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.serino.data.model.DomainProduct


@Database(entities = [DomainProduct::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract val productDatabaseDao: ProductDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getInstance(context: Context): ProductDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductDatabase::class.java,
                        "contact_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}