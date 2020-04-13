package com.example.barberi.admin

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.barberi.BasesDatos.Productos

import com.example.barberi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_registro__productos.*
import kotlinx.android.synthetic.main.fragment_registro__productos.view.*
import kotlinx.android.synthetic.main.fragment_registro__productos.view.et_nameProducto

/**
 * A simple [Fragment] subclass.
 */
class RegistroProductosFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_registro__productos,container,false)


        auth = FirebaseAuth.getInstance()
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("Productos")

            root.button2.setOnClickListener {

            var descrip: String = et_nameProducto.text.toString()
            var prize: String = et_Precio.text.toString()
            var canti: String = et_Cantidad.text.toString()
            if (TextUtils.isEmpty(et_nameProducto.text.toString())){
                et_nameProducto.error = "Ingrese nombre o descripcion del producto"
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(et_Precio.text.toString())) {
                et_Precio.error = "Ingrese el valor del producto"
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(et_Cantidad.text.toString())) {
                et_Cantidad.error = "Ingrese la cantidad"

            }
            else if (et_nameProducto.text.isEmpty()  || et_Precio.text.isEmpty() || et_Cantidad.text.isEmpty() ) {
                Toast.makeText(activity, " Completar datos", Toast.LENGTH_SHORT).show()
            }
/*
        val descripcion = "Gomina"

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("productos")
        var id: String? = myRef.push().key

        val  productos= Productos(id!!,descripcion,1000)
        myRef.child(id).setValue(productos)

        */
            else  {
                // Firebase  base de datos
                // ingrese los usuarios que van a acceder a la barberia, entonces estos se guardan en la base de datos

                var id: String? = myRef.push().key
                val productos = Productos(id!!,descrip,prize.toInt(),canti.toInt())

                myRef.child(id).setValue(productos)

            }
        }




        // Inflate the layout for this fragment
        return root
    }

}
