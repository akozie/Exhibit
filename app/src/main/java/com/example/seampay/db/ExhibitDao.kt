package com.example.seampay.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seampay.model.Exhibit
import kotlinx.coroutines.flow.Flow

// Data Class to store the data
@Dao
interface ExhibitDao {

    // Query to fetch all the data from the
    // SQLite database
    @Query("SELECT * FROM iphone")
    // Kotlin flow is an asynchronous stream of values
    fun getExhibits(): Flow<List<Exhibits>>

    // If a new data is inserted with same primary key
    // It will get replaced by the previous one
    // This ensures that there is always a latest
    // data in the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExhibits(exhibit: List<Exhibits>)

}