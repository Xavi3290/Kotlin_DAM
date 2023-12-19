package com.example.pr2xavierroca.BD

import androidx.room.*


@Entity(tableName = "series")
data class Serie(
    @PrimaryKey(autoGenerate = true)
    var idSerie: Int?,
    @ColumnInfo(name="titol",index = true)  // index= true tindra un index per busca rapida, para hacer filtraciones de un campo
    var titol: String,
    @ColumnInfo( defaultValue = "Acció")
    var genere: String,
    var numTemp:Int

)

@Entity(
    tableName = "capitols",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Serie::class,
            parentColumns = arrayOf("idSerie"),   //primaryKey de Usuari
            childColumns = arrayOf("idSerie"),  //foreignKey de Carrito
            onDelete = ForeignKey.CASCADE,
        ),
    ),
    indices = [          // Obligatori que la FK tingui un index
        Index("idSerie"),
    ],
)
data class Capitol(
    @PrimaryKey(autoGenerate = true)
    var idCapitol: Int?,
    //@ForeignKey()
    @ColumnInfo(name="titol",index = true)
    var titol:String,
    var numTemp:Int,
    var important:Int,
    var durada:Int,
    var idSerie:Int
)
