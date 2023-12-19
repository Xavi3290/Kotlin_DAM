package com.example.activitat10.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey
    val nomusu: String,
    val contra: String,
    val tipus: String
)

@Entity(
    primaryKeys= arrayOf("codiproducte", "nomusu"),
    foreignKeys = arrayOf(ForeignKey(
    entity = User::class,
    parentColumns = arrayOf("nomusu"),
    childColumns = arrayOf("nomusu")
)),
indices = arrayOf(Index("nomusu"))
)
data class ProducteCarret(
    val codiproducte: Int,
    var qtt: Int,
    val nomusu: String
)