package com.example.seampay.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.seampay.model.ImagesList

@Entity(tableName = "iphone")
data class Exhibits(
    @PrimaryKey
    val id: Int,
    val title: String,
    @Embedded
    val images: ImagesList
)