package com.marjasoft.ejcamerax.bd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Media (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val descripcio: String,
    val url: String,
    val tipus: String,
    val datahora: String
)