package com.example.barberi.BasesDatos



import androidx.room.*


@Dao

interface ProductosDAO {

    @Query("SELECT * FROM Productos")
    fun getAll(): List<Productos>

    @Query("SELECT * FROM Productos WHERE descripcion=:descrip")
    fun findByName(descrip: String): Productos

    @Insert
    fun insertAllUser(vararg productos: Productos)

    @Insert
    fun insertUser(productos: Productos)

    @Delete
    fun deleteUser(productos: Productos)

    @Update
    fun updateUser(productos: Productos)

    @Query("SELECT * FROM Productos")
    fun getProductos(): List<Productos>


}