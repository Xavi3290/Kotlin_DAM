package com.example.notification


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


/*
https://www.develou.com/crear-notificaciones-en-android/
https://developer.android.com/guide/topics/ui/notifiers/notifications
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt1 = findViewById<Button>(R.id.bt1)
        val notificacio = Notificacio(this)

        bt1.setOnClickListener {
            notificacio.generaNoticacioText(getString(R.string.titol), getString(R.string.descripcio), getString(R.string.textLlarg))
            notificacio.generaNoticacioImatge(getString(R.string.titol), getString(R.string.descripcio), R.drawable.cvlogo)
            notificacio.generaNotificacioMedia("David Guetta", "Titanium")

            val dades = arrayListOf<String>("Missatge 1", "Missatge 2", "Missatge 3", "Missatge 4")
            notificacio.generaNotificacioInbox(dades)

            notificacio.generaNotificacioProgress("Instal·lant ...")
        }
    }

}