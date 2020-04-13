package com.example.barberi.admin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.barberi.BasesDatos.Barberos
import com.example.barberi.Login

import com.example.barberi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_registro__barber.*
import kotlinx.android.synthetic.main.fragment_registro__barber.view.*

/**
 * A simple [Fragment] subclass.
 */

/*
        val descripcion = "Gomina"

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("productos")
        var id: String? = myRef.push().key

        val  productos= Productos(id!!,descripcion,1000)
        myRef.child(id).setValue(productos)

        */
class RegistroBarberFragment : Fragment() {


    // base de datos
    private lateinit var auth: FirebaseAuth



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_registro__barber,container,false)


        auth = FirebaseAuth.getInstance()
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("Barberos")


            root.button.setOnClickListener {
            var nombre: String = et_nameBarber.text.toString()
            var passw: String = et_contrasBarber.text.toString()
            var passComp: String = et_passBarber.text.toString()
            if (TextUtils.isEmpty(et_nameBarber.text.toString())){
                et_nameBarber.error = "Ingrese nombre de usuario"
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(et_contrasBarber.text.toString())) {
                et_contrasBarber.error = "Ingrese Contraseña"
                return@setOnClickListener
            }
            else if (et_nameBarber.text.isEmpty()  || et_contrasBarber.text.isEmpty() ) {
                Toast.makeText(activity, " Completar datos", Toast.LENGTH_SHORT).show()
            }
            else if (et_contrasBarber.text.toString() != et_passBarber.text.toString()) {
                Toast.makeText(activity, " Contraseñas no coinciden", Toast.LENGTH_SHORT).show()

            }
            else if (et_contrasBarber.text.length < 6 ) {
                Toast.makeText(activity, " La contrasena debe ser minimo de 6 caracteres", Toast.LENGTH_SHORT).show();

            }
            else if (et_contrasBarber.text.length >= 6) {
                // Firebase  base de datos
                // ingrese los usuarios que van a acceder a la barberia, entonces estos se guardan en la base de datos

                var iD: String? = myRef.push().key
                val barberos = Barberos(iD!!,nombre)
                myRef.child(iD).setValue(barberos)

                // Firebase Autenticacion
                auth.createUserWithEmailAndPassword(nombre,passw)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful){
                            Log.d("Login","Create  Barber success")
                            val user: FirebaseUser? = auth.currentUser
                            myRef.child(iD).setValue(barberos)

                        }else{
                            Log.w("Login","Failure",task.exception)

                        }
                    }


            }
        }




        // Inflate the layout for this fragment
        return root
    }







}
