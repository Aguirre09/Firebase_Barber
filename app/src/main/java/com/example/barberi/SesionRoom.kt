package com.example.barberi

import android.app.Application
import androidx.room.Room
import com.example.barberi.BasesDatos.MyDatabase

class SesionRoom : Application() {

    companion object {
        lateinit var database: MyDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            MyDatabase::class.java,
            "MyDatabase_DB"
        )
            .allowMainThreadQueries()
            .build()
    }
}