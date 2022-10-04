package com.example.bitfit1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CaloriesDAO {
    @Query("SELECT * FROM calories_table")
    fun getAll(): Flow<List<CaloriesEntity>>

    @Insert
    fun insertAll(calories: List<CaloriesEntity>)

    @Insert
    fun insert(calories: CaloriesEntity)

    @Query("DELETE FROM calories_table")
    fun deleteAll()
}