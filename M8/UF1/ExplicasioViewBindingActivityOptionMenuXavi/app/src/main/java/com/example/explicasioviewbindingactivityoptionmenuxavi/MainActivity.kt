package com.example.explicasioviewbindingactivityoptionmenuxavi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.explicasioviewbindingactivityoptionmenuxavi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tv1.text = "Text en el textview"
        binding.but1.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("grup","DAM2")
            intent.putExtra("NotaMitjana", 9)
            startActivity(intent)
        }
    }

    override fun onStart() {
        Toast.makeText(this,"onStart",Toast.LENGTH_LONG).show()
        super.onStart()
    }
    override fun onStop(){
        Toast.makeText(this,"onStop",Toast.LENGTH_LONG).show()
        super.onStop()
    }
    override fun onResume(){
        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater.inflate(R.menu.optionmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when{
            item.itemId == R.id.fercast -> Toast.makeText(this,"Vols fer Cast",Toast.LENGTH_LONG).show()
            item.itemId == R.id.fershare -> Toast.makeText(this,"Vols fer share",Toast.LENGTH_LONG).show()
            item.itemId == R.id.anar2act ->{
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("grup","DAM2")
                intent.putExtra("NotaMitjana", 9)
                startActivity(intent)
            }
            item.itemId == R.id.sop1 -> Toast.makeText(this,"Vols fer Subopcio 1",Toast.LENGTH_LONG).show()
            item.itemId == R.id.sop2 -> Toast.makeText(this,"Vols fer Subopcio 2",Toast.LENGTH_LONG).show()
        }
        return true
    }
}