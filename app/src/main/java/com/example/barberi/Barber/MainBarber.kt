package com.example.barberi.Barber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barberi.BasesDatos.Productos
import com.example.barberi.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainBarber : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_barber)


/*
        val descripcion = "Gomina"

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("productos")
        var id: String? = myRef.push().key

        val  productos= Productos(id!!,descripcion,1000)
        myRef.child(id).setValue(productos)

        */

    }
}

