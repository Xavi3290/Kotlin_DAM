package com.example.exemplebdromm.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity( tableName = "departaments" )
data class Departament (
    @PrimaryKey(autoGenerate = true)
    val codi: Int?,
    @ColumnInfo(name = "nomdepartament")
    val nomDepartament: String,
    )

@Entity(
    tableName = "empleats",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Departament::class,
            parentColumns = arrayOf("codi"),
            childColumns = arrayOf("departament"),
            onDelete = CASCADE,
        ),
    ),
    indices = [          // Obligatori que la FK tingui un index
        Index("departament"),
    ],
)
data class Empleat (
    @PrimaryKey( autoGenerate = true)
    val codi: Int?,
    @ColumnInfo( name = "nomempleat", index = true)
    val nomEmpleat: String,
    val sou: Double,
    @ColumnInfo( defaultValue = "Informàtica")
    val funcio: String?,
    val departament: Int
    )