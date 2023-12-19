package com.example.activitat5

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.activitat5.databinding.ActivityMain2Binding
import com.example.activitat5.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding  // para que el binding funcione, en cada activity tienes que poner su respectivo nombre


    // lo primero que se crea en la activity
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain2Binding.inflate(layoutInflater)  // se ha de cambiar para que el binding funcione
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // action bar para que te sale arriba en la que tienes que clicar para volver, necesita un onOptionsItemSelected para volver
        val actionBar = supportActionBar
        actionBar!!.title = "Segona Activity"
        actionBar.setDisplayHomeAsUpEnabled(true)  // flecha de tornar

        //Las variables que coges de la activity1
        val pass = intent.getStringExtra("password").toString()         // asi recojes informacion de otra activity
        val check = intent.getBooleanExtra("check",true)
        if(pass.contentEquals("1234") && check){        // te mira que el password y el check que envias sean correctos, sino no te deja ir a la activity
            Toast.makeText(this,"Password correcte, check correcte", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Password incorrecte", Toast.LENGTH_LONG).show()
            finish()
        }
        // listener para el boton, se hace aqui no en el optionsItemSelected, que es para el menu, apretas el boton y que te envie lo que quieras
        binding.benviar.setOnClickListener {
            var adresa = binding.etusuari.text.toString()       // Asi recojes la informacion del layout
            var adresses = arrayOf(adresa)
            var sub = binding.etasumpte.text.toString()
            var cos = binding.etcos.text.toString()
            enviarEmail(adresses,sub,cos)
        }

    }

    // para que te envie del menu a otra activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(android.R.id.home == item.itemId) finish()   // para que vuelva a la primera con el action bar // el finish() es para cerrar la activity
        return true
    }

    // intent implicito para enviar en email, estan la informacion en los intent implicitos del moodle
    fun enviarEmail(adresses:Array<String>,subject:String,contingut:String){
        val intent = Intent(Intent.ACTION_SENDTO).apply{
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, adresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, contingut)
        }
        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }

    }


}