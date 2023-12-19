package com.example.activitatmediarecorder.BD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grabacions")
data class Grabacio(
    @PrimaryKey()
    val codi: String?,
)