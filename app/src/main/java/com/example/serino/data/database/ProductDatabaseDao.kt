package com.example.serino.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.serino.data.model.DomainProduct

@Dao
interface ProductDatabaseDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(product: DomainProduct)

    @Query("SELECT * FROM product_database")
    fun getAll(): LiveData<List<DomainProduct>>
}