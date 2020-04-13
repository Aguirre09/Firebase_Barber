package com.example.barberi.BasesDatos



import androidx.room.*

@Dao

interface BarberosDAO {

    @Query("SELECT * FROM Barberos")
    fun getAll():List<Barberos> //

    @Query("SELECT * FROM Barberos WHERE Name=:name  ")
    fun findByName(name:String): Barberos

    @Insert
    fun insertAllUser(vararg barberos: Barberos)

    @Insert
    fun insertUser(barberos: Barberos)

    @Delete
    fun deleteUser(barberos: Barberos)

    @Update
    fun updateUser(barberos: Barberos)
}