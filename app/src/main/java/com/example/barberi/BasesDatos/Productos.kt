package com.example.barberi.BasesDatos


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Productos (

    @PrimaryKey @ColumnInfo(name = "id")var id: String="",
    @ColumnInfo(name = "descripcion") var descrip: String="",
    @ColumnInfo(name = "Precio") var pesos:Int=0,
    @ColumnInfo(name = "cantidad") var cantidad:Int=0
)