package com.example.barberi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.timerTask

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer = Timer()
        timer.schedule(timerTask {
            goToLogin()

        }, 2000 )

    }

    private fun goToLogin() {
        val intent = Intent(this , Login::class.java)
        startActivity(intent)
        finish()
    }
}
