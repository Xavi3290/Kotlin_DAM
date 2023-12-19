package com.example.activitat5

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.activitat5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding       // para que el binding funcione, en el build.gradle(module) tienes que copiar
    // buildFeatures{
    // viewBinding true
    //}
    private lateinit var menu2: Menu    // para que el check box pueda ir a la otra activity

    // lo primero que se crea en la activity
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)       // se ha de cambiar para que el binding funcione
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {        // per enllaçar el option menu amb l'activity
        val inflater = menuInflater.inflate(R.menu.optionmenu,menu)     // el option menu se crea en New/Android resource file y en resource type Menu
        menu2 = menu!!    // para poder cojer el checkbox en otra activity
        return true
    }

    fun goURL(url:String){      // intent implicit per que et vagi a una web
        // Obrir una pagina web en el navegador predeterminat
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    // para que en el meu lo que selecionas te lleve a otra activity o haga lo que quieres que haga
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //when(item.itemId){ asi no tienes que igualar item.itemId == R.id.loquevenga
        when{
            item.itemId == R.id.web -> goURL("https://www.youtube.com/")
            item.itemId == R.id.fercast -> Toast.makeText(this,"Vols fer Cast", Toast.LENGTH_LONG).show()
            item.itemId == R.id.anar2activity ->{
                Toast.makeText(this,"Activity2", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }
            item.itemId == R.id.sop1 ->{
                Toast.makeText(this,"Activity2", Toast.LENGTH_LONG).show()  //Para que salga un cartelito para provar
                val intent = Intent(this, MainActivity2::class.java)    //Asi envias informacion a otra activity
                intent.putExtra("password",binding.etpass.text.toString())          // el putExtra envia la info
                val check = menu2.getItem(0)?.isChecked     // el checkbox es como u array de 2 por eso decimos una posicion, el ? por si puede ser nulo
                intent.putExtra("check", check)
                // intent.putExtra("chec", R.id.check.) mal, tenemos que poner el menu2 primero sino da error
                startActivity(intent)
            }
            item.itemId == R.id.sop2 ->{
                Toast.makeText(this,"Activity3", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity3::class.java)        //Asi envias informacion a otra activity
                intent.putExtra("password",binding.etpass.text.toString())
                val check = menu2.getItem(0)?.isChecked
                intent.putExtra("check", check)
                // intent.putExtra("chec", R.id.check.) mal, tenemos que poner el menu2 primero sino da error
                startActivity(intent)
            }
            item.itemId == R.id.check -> {
                item.isChecked = !item.isChecked    // para que vaya cambiando el check
               // if(item.isChecked()) {
               //     item.setChecked(false)
               // }
               // else{
                //    item.setChecked(true)
               // }
            }
        }
        return true
    }
}