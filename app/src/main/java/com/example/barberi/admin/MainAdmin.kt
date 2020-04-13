package com.example.barberi.admin

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.barberi.MapFragment
import com.example.barberi.R
import kotlinx.android.synthetic.main.activity_main_admin.*

class MainAdmin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_admin)

        loadFragment(MapFragment())
        bottomNavigationAdmin.setOnNavigationItemSelectedListener  {  menuItem ->
            when {
                menuItem.itemId == R.id.navigationExplore -> {
                    //loadFragment(InicioFragment())
                    loadFragment(MapFragment())
                    Toast.makeText(this, "Barberia", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }
                menuItem.itemId == R.id.navigationRegistroBarber -> {
                    //loadFragment(InicioFragment())
                    loadFragment(RegistroBarberFragment())
                    Toast.makeText(this, "Barberos", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }
                menuItem.itemId == R.id.navigationRegistroProductos -> {
                    loadFragment(RegistroProductosFragment())
                    Toast.makeText(this, "Productos", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }

                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }



    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.fragmentContainer,fragment)
            fragmentTransaction.commit()

        }
    }
    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
        super.onBackPressed()
    }






    /*     auth = FirebaseAuth.getInstance()
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("Barberos")



        button.setOnClickListener {
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
        }*/


}
