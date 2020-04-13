package com.example.barberi

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.barberi.BasesDatos.Barberos
import com.example.barberi.BasesDatos.MyDatabase
import com.example.barberi.BasesDatos.Usuario

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*
import java.sql.Types

class Registro : AppCompatActivity() {


    // base de datos

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        auth = FirebaseAuth.getInstance()
        // base
       // db = Room.databaseBuilder(applicationContext,MyDatabase::class.java,"MyDatabase").allowMainThreadQueries().build()

        //






// BOTON REGISTRAR
        et_register.setOnClickListener {
            var name: String = et_name.text.toString()
            var pass: String = et_contras.text.toString()
            var passComp: String = et_passC.text.toString()
            if (TextUtils.isEmpty(et_name.text.toString())){
                et_name.error = "Ingrese nombre de usuario"
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(et_contras.text.toString())) {
                et_contras.error = "Ingrese Contraseña"
                return@setOnClickListener
            }
            else if (et_name.text.isEmpty()  || et_contras.text.isEmpty() ) {
                Toast.makeText(this, " Completar datos", Toast.LENGTH_SHORT).show()
            }
            else if (et_contras.text.toString() != et_passC.text.toString()) {
              Toast.makeText(this, " Contraseñas no coinciden", Toast.LENGTH_SHORT).show()

            }
            else if (et_contras.text.length < 6 ) {
                Toast.makeText(this, " La contrasena debe ser minimo de 6 caracteres", Toast.LENGTH_SHORT).show()
            }
            else if (et_contras.text.length >= 6) {
                // Firebase  base de datos
                // ingrese los usuarios que van a acceder a la barberia, entonces estos se guardan en la base de datos
                val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                val myRef: DatabaseReference = database.getReference("Usuario")
                var id: String? = myRef.push().key
                val usuario = Usuario(id!!,name)
                myRef.child(id).setValue(usuario)

                // Firebase Autenticacion
                auth.createUserWithEmailAndPassword(name,pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful){
                            Log.d("Login","Create user success")
                            val user: FirebaseUser? = auth.currentUser
                            myRef.child(id).setValue(usuario)
                            goToLoginActivity()
                        }else{
                            Log.w("Login","Failure",task.exception)
                            Toast.makeText(baseContext,"AUthentication failed",Toast.LENGTH_SHORT).show()
                        }

                    }





            }
        }



        // ROOM
        /*
        et_register.setOnClickListener {

            if (TextUtils.isEmpty(et_name.text.toString())){
                et_name.error = "Ingrese nombre de usuario"
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(et_contras.text.toString())) {
                et_contras.error = "Ingrese Contraseña"
                return@setOnClickListener
            }
            else if (et_name.text.isEmpty()  || et_contras.text.isEmpty() ) {
                Toast.makeText(this, " Completar datos", Toast.LENGTH_SHORT).show()
            }
            //else if (et_contras.text.toString() != et_passC.text.toString()) {
            //  Toast.makeText(this, " Contraseñas no coinciden", Toast.LENGTH_SHORT).show()

            //}
            else if (et_contras.text.length < 6 ) {
                Toast.makeText(this, " La contrasena debe ser minimo de 6 caracteres", Toast.LENGTH_SHORT).show()
            }
            else if (et_contras.text.length >= 6) {
                // Ingresa a la base de datos
                val usuario = Usuario(Types.NULL,et_name.text.toString(),et_contras.text.toString())
                Thread(Runnable{
                    db.usuarioDao().insertUser(usuario)
                    runOnUiThread{
                        et_name.setText("")
                        et_contras.setText("")
                    }
                }).start()
                goToLoginActivity()
            }

        }
        */

 //////ROOM ////////

        // YA TIENE CUENTA
        Already.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)

        }




    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
        super.onBackPressed()
    }


    private fun goToLoginActivity() {
        val intent = Intent(this,Login::class.java)
        startActivity(intent)
    }











}
