package com.example.barberi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.barberi.Barber.MainBarber
import com.example.barberi.admin.MainAdmin

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    //private lateinit var db: MyDatabase
    private lateinit var auth: FirebaseAuth





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        //db = Room.databaseBuilder(applicationContext, MyDatabase::class.java,"MyDatabase").allowMainThreadQueries().build()


        // login con google google_login_btn





        // LOGGIN

        auth = FirebaseAuth.getInstance()

            // perfiles

        var perfil = getString(R.string.B)
        et_Barber.setOnClickListener {
            perfil = getString(R.string.B)
        }

        et_Usuario.setOnClickListener {
            perfil=getString(R.string.U)
        }

        et_Admin.setOnClickListener {
            perfil= getString(R.string.A)
        }

        /////////////////////////////////////////////////



        // lOGGIN FIREBASE
        et_Ingreso.setOnClickListener {

            if (TextUtils.isEmpty(user.text.toString())) {
                user.error = "Ingrese nombre de usuario"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(contra.text.toString())) {
                contra.error = "Ingrese Contraseña"
                return@setOnClickListener
            }


            if (perfil == "Barbero") {

                auth.signInWithEmailAndPassword(user.text.toString(), contra.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user: FirebaseUser? = auth.currentUser
                            val intent = Intent(this, MainBarber::class.java)
                            startActivity(intent)
                        } else {
                            Log.w("Login", "Failure", task.exception)
                            Toast.makeText(baseContext, "Auth failed", Toast.LENGTH_SHORT).show()
                        }

                    }
            } else if (perfil == "Usuario") {
                auth.signInWithEmailAndPassword(user.text.toString(), contra.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user: FirebaseUser? = auth.currentUser
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Log.w("Login", "Failure", task.exception)
                            Toast.makeText(baseContext, "Auth failed", Toast.LENGTH_SHORT).show()
                        }

                    }

            } else if (perfil == "Admin") {
                auth.signInWithEmailAndPassword(user.text.toString(), contra.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user: FirebaseUser? = auth.currentUser
                            val intent = Intent(this, MainAdmin::class.java)
                            startActivity(intent)
                        } else {
                            Log.w("Login", "Failure", task.exception)
                            Toast.makeText(baseContext, "Auth failed", Toast.LENGTH_SHORT).show()
                        }

                    }

            }
        }


        // LOgin con GOOGLE



        /*
 /// ROOM
        et_Ingreso.setOnClickListener {
            // ROOM
            Thread(Runnable{
                val usuario = db.usuarioDao().findByName(user.text.toString())
                val barberos= db.barberosDao().findByName(user.text.toString())
                runOnUiThread{
                    if((usuario != null ) && (perfil.text.toString() != null)) {
                        if ( (usuario.nombre == user.text.toString() && (perfil.text.toString() == "USUARIO")  )){
                            if (usuario.contrasenaa == contra.text.toString()){
                                val intent = Intent(this,MainActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }

                    // Ingreso de Barberos

                    if  ((barberos != null ) && (perfil.text.toString() != null)){
                        if ( (barberos.name== user.text.toString()) && (perfil.text.toString() == "BARBER")){
                            if (barberos.passw == contra.text.toString()){
                                val intent = Intent(this,MainBarber::class.java)
                                startActivity(intent)
                            }

                        }

                    }

                    else{

                        textView.text = "Usuario " + usuario + " no encontrado"
                        //val intent = Intent(this,Registro::class.java)
                        //startActivity(intent)
                    }


                }
            }).start()

        }


        * /

         */
        // REGISTRO
        et_registro.setOnClickListener {
            val intent= Intent(this, Registro::class.java)
            startActivityForResult(intent,1)
        }

        // Registro barbero


    }


}
