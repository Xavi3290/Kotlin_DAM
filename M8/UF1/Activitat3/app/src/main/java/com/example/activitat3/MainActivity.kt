package com.example.activitat3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cv1 = findViewById<CalendarView>(R.id.cv1)

        cv1.setOnDateChangeListener{ calendarView, any, mes, dia ->

            //Toast.makeText(this,"Data escollida: $dia/${mes+1}/$any", Toast.LENGTH_LONG).show()  // et diu el dia que selecciones
            var avui = LocalDate.now()
            //Toast.makeText(this, "Avui és $avui", Toast.LENGTH_LONG).show()   // una altre manera de mostrar el dia
            var dataescollida = LocalDate.of(any,mes +1,dia)

            if(avui.isAfter(dataescollida))
                Toast.makeText(this, "La data escollida $dataescollida es anterior a la data d'avui $avui", Toast.LENGTH_LONG).show()
            else if(avui.isEqual(dataescollida))
                Toast.makeText(this, "La data escollida és avui: $dataescollida", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "La data escollida $dataescollida és posterior a la data d'avui $avui", Toast.LENGTH_LONG).show()
        }

    }
}