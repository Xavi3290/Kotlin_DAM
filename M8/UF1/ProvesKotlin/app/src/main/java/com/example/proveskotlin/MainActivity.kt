package com.example.proveskotlin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.proveskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var menu2: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater.inflate(R.menu.optionmenu, menu)
        menu2 = menu!!
        return true
    }
    fun goURL(url:String){      // intent implicit per que et vagi a una web
        // Obrir una pagina web en el navegador predeterminat
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mweb -> goURL("https://www.youtube.com/")
            R.id.mcheck -> item.isChecked = !item.isChecked
            R.id.mgmail ->{
                val intent = Intent(this, EmailActivity::class.java)
                val check = menu2.getItem(0)?.isChecked
                intent.putExtra("passord", binding.etPass.text.toString())
                intent.putExtra("check", check)
                startActivity(intent)
            }
            R.id.mcalendari ->{
                val intent = Intent(this, CalendarActivity::class.java)
                val check = menu2.getItem(0)?.isChecked
                intent.putExtra("password",binding.etPass.text.toString())
                intent.putExtra("check", check)
                startActivity(intent)
            }
        }
        return true
    }





}