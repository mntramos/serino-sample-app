package com.example.serino.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.serino.data.model.DatabaseProduct

@Dao
interface ProductDatabaseDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(products: List<DatabaseProduct>)

    @Query("SELECT * FROM databaseproduct")
    fun getAll(): LiveData<List<DatabaseProduct>>
}