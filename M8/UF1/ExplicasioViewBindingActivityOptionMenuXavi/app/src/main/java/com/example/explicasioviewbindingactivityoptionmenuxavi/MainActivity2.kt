package com.example.explicasioviewbindingactivityoptionmenuxavi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.explicasioviewbindingactivityoptionmenuxavi.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain2Binding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val grup = intent.getStringExtra("grup").toString()
        val nota = intent.getIntExtra("NotaMitjana",0)

        binding.tvActivity2.text = grup +". NotaMitjana "+nota

        val actionBar = supportActionBar
        actionBar!!.title = "Segona Activity"
        actionBar.setDisplayHomeAsUpEnabled(true)  // flecha de tornar





    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater.inflate(R.menu.optionmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when{
            item.itemId == R.id.fercast -> Toast.makeText(this,"Vols fer Cast", Toast.LENGTH_LONG).show()
            item.itemId == R.id.fershare -> Toast.makeText(this,"Vols fer share", Toast.LENGTH_LONG).show()
            item.itemId == R.id.anar2act ->{
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("grup","DAM2")
                intent.putExtra("NotaMitjana", 9)
                startActivity(intent)
            }
            item.itemId == R.id.sop1 -> Toast.makeText(this,"Vols fer Subopcio 1", Toast.LENGTH_LONG).show()
            item.itemId == R.id.sop2 -> Toast.makeText(this,"Vols fer Subopcio 2", Toast.LENGTH_LONG).show()
            item.itemId == android.R.id.home -> finish()    // para que vaya para atras con la flechita de arriba
        }
        return true
    }
}