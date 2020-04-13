package com.example.barberi.BasesDatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Usuario (
    @PrimaryKey @ColumnInfo(name = "id")var id: String ="",
    @ColumnInfo(name = "Nombre") var nombre: String=""
    //@ColumnInfo(name = "Contrasenaa") var contrasenaa:String=""
)