package com.example.seampay.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.seampay.util.Converters


@TypeConverters(Converters::class)
@Database(entities = [Exhibits::class], version = 1)
abstract class ExhibitDatabase : RoomDatabase() {

    abstract fun exhibitDao(): ExhibitDao
}