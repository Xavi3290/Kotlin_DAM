package com.example.activitat1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val primernumero = findViewById<EditText>(R.id.et1)
        val segonnumero = findViewById<EditText>(R.id.et2)
        val resultat = findViewById<EditText>(R.id.etResultat)
        val suma = findViewById<Button>(R.id.suma)
        val resta = findViewById<Button>(R.id.resta)
        val multi = findViewById<Button>(R.id.multiplicacio)
        val divi = findViewById<Button>(R.id.divisio)

        suma.setOnClickListener {
            var num1 = primernumero.text.toString().toDouble()
            var num2 = segonnumero.text.toString().toDouble()
            var resu = num1 + num2
            resultat.setText(resu.toString())
        }

        resta.setOnClickListener {
            var num1 = primernumero.text.toString().toDouble()
            var num2 = segonnumero.text.toString().toDouble()
            var resu = num1 - num2
            resultat.setText(resu.toString())
        }

        multi.setOnClickListener {
            var num1 = primernumero.text.toString().toDouble()
            var num2 = segonnumero.text.toString().toDouble()
            var resu = num1 * num2
            resultat.setText(resu.toString())
        }

        divi.setOnClickListener {
            var num1 = primernumero.text.toString().toDouble()
            var num2 = segonnumero.text.toString().toDouble()
            var resu = num1 / num2
            resultat.setText(resu.toString())
        }



    }
}