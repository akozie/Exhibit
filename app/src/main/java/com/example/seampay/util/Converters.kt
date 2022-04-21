package com.example.seampay.util

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromString(string: String): List<String> {
        return string.split(",").map { it }
    }
    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(",")
    }
}