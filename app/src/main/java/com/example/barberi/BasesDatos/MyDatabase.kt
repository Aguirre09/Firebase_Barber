package com.example.barberi.BasesDatos

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Usuario::class,Barberos::class,Productos::class),version = 1)



abstract class MyDatabase: RoomDatabase() {
    abstract fun usuarioDao() : UsuarioDAO
    abstract fun barberosDao(): BarberosDAO
    abstract fun productosDao() : ProductosDAO



}