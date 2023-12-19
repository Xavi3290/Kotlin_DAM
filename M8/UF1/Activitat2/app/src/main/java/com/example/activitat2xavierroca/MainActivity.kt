package com.example.activitat2xavierroca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // private lateinit var text: String    // Inicializarla al principio, lateinit significa que la inicializaras mas tarde

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Agafar elementnts de la part visual

        val et1 = findViewById<EditText>(R.id.et1)
        val bAfegir = findViewById<Button>(R.id.bafegir)
        val tv1 = findViewById<TextView>(R.id.tv1)

        //Click al butó
        // var text : String     // inisialitzar sense valor
        var text = et1.getText().toString()

        bAfegir.setOnClickListener{
            text += "${et1.text}\n"
           // text += ("\n"+ et1.text)     // Fet perl profe
            tv1.setText(text)
           // var text = et1.getText().toString()   //Con el append tiene que estar dentro el gettext dentro del listener
           // tv1.append(text+"\n")  // Hacen lo mismo
           // tv1.text = tv1.text.toString() + "\n" + et1.text.toString()  //Como lo ha hecho el profe, solo con esta frase

        }


    }
}