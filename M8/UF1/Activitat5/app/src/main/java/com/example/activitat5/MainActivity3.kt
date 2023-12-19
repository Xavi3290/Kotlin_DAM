package com.example.activitat5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.MenuItem
import android.widget.Toast
import com.example.activitat5.databinding.ActivityMain2Binding
import com.example.activitat5.databinding.ActivityMain3Binding
import java.util.*

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding  // para que el binding funcione, en cada activity tienes que poner su respectivo nombre

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain3Binding.inflate(layoutInflater)  // se ha de cambiar para que el binding funcione
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // action bar para que te sale arriba en la que tienes que clicar para volver, necesita un onOptionsItemSelected para volver
        val actionBar = supportActionBar
        actionBar!!.title = "Tercera Activity"
        actionBar.setDisplayHomeAsUpEnabled(true)  // flecha de tornar

        val pass = intent.getStringExtra("password").toString()
        val check = intent.getBooleanExtra("check",true)
        if (pass.contentEquals("1234") && check) {      // te mira que el password y el check que envias sean correctos, sino no te deja ir a la activity
            Toast.makeText(this, "Password correcte i check correcte", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Password incorrecte", Toast.LENGTH_LONG).show()
            finish()
        }
        // listener para el boton, se hace aqui no en el optionsItemSelected, que es para el menu, apretas el boton y que te envie lo que quieras
        binding.benviar2.setOnClickListener {
            var titol = binding.ettitol.text.toString()
            var AnyI = binding.etanyin.text.toString().toInt()      // convertir-ho en int o amb parse
            var MesI = binding.etmesin.text.toString().toInt()
            var DiaI = binding.etdiain.text.toString().toInt()
            var AnyF = binding.etanyfi.text.toString().toInt()
            var MesF = binding.etmesfi.text.toString().toInt()
            var DiaF = binding.etdiafi.text.toString().toInt()
            var inici = Calendar.getInstance()
            var final = Calendar.getInstance()
            inici.set(AnyI, MesI, DiaI,0,0)
            final.set(AnyF,MesF,DiaF,0,0)
            addEvent(titol,inici.timeInMillis, final.timeInMillis)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(android.R.id.home == item.itemId) finish()   // para que vuelva a la primera con el action bar // el finish() es para cerrar la activity
        return true
    }

    // intent implicit per posar un titol y hora de inici y final al calendari
    fun addEvent( title:String, begin:Long, end: Long){
        val intent = Intent(Intent.ACTION_INSERT).apply{
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, title)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
        }
        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }
}