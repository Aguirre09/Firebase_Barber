package com.example.barberi.BasesDatos

import androidx.room.*

@Dao

interface UsuarioDAO {

    @Query("SELECT * FROM Usuario")
    fun getAll():List<Usuario> //

    @Query("SELECT * FROM usuario WHERE nombre= :nombre")
    fun findByName(nombre:String): Usuario

    @Insert
    fun insertAllUser(vararg usuarios: Usuario)

    @Insert
    fun insertUser(usuario: Usuario)

    @Delete
    fun deleteUser(usuario: Usuario)

    @Update
    fun updateUser(usuario: Usuario)


}