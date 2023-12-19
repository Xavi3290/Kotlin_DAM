package com.example.activitat9bona.BD

import androidx.room.*


@Entity(tableName = "usuaris")
data class Usuari(
    @PrimaryKey(autoGenerate = true)
    val codi: Int?,
    @ColumnInfo(index = true)  // index= true tindra un index per busca rapida, para hacer filtraciones de un campo
    val nom: String,
    val password: String,
    val tipus: Int?
)

@Entity(
    tableName = "carritos",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Usuari::class,
            parentColumns = arrayOf("codi"),   //primaryKey de Usuari
            childColumns = arrayOf("codiUsuari"),  //foreignKey de Carrito
            onDelete = ForeignKey.CASCADE,
        ),
    ),
    indices = [          // Obligatori que la FK tingui un index
        Index("codiUsuari"),
    ],
)
data class Carritos(
    @PrimaryKey(autoGenerate = true)
    var codi: Int?,
    //@ForeignKey()
    val codiUsuari: Int?,
    val codiProducte:Int,
    val imatge:String,
    val descripcio:String,
    val descripcioCompleta:String,
    val preu:Double,
    val tipusProducte:Int,
    val tipusCardview:Int

)
