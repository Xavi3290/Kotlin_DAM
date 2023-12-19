package com.example.activitat5maps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat5maps.bd.Ubicacio
import com.example.activitat5maps.bd.dades
import com.example.activitat5maps.bd.database
import com.example.activitat5maps.databinding.ActivityParametresRutaBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ParametresRuta : AppCompatActivity() {
    private lateinit var binding: ActivityParametresRutaBinding
    lateinit var adapterOrigen : Adaptador
    lateinit var adapterDesti : Adaptador
    var metode = "Cotxe"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParametresRutaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        runBlocking {
            val corrutina = launch {
                dades = database.getDatabase(this@ParametresRuta).DaoUbicacions().selectAll()
            }
            corrutina.join()
        }
        adapterOrigen = Adaptador(dades)
        adapterDesti = Adaptador(dades)
        binding.rvOrigen.adapter = adapterOrigen
        binding.rvOrigen.layoutManager = LinearLayoutManager(this)

        binding.rvDesti.adapter = adapterDesti
        binding.rvDesti.layoutManager = LinearLayoutManager(this)

        binding.anarcotxe.setOnClickListener {
            if (metode=="Peu"){
                metode = "Cotxe"
                binding.anarcotxe.background = getDrawable(R.drawable.background_button_selected)
                binding.anarpeu.background = getDrawable(R.drawable.background_buttons)
            }
        }
        binding.anarpeu.setOnClickListener {
            if (metode=="Cotxe"){
                metode = "Peu"
                binding.anarpeu.background = getDrawable(R.drawable.background_button_selected)
                binding.anarcotxe.background = getDrawable(R.drawable.background_buttons)
            }
        }

        binding.BTRuta.setOnClickListener {
            if (adapterOrigen.getSeleccionat() == null){
                Toast.makeText(this, "No s'ha seleccionat l'origen", Toast.LENGTH_LONG).show()
            }else {
                if (adapterDesti.getSeleccionat() == null){
                    Toast.makeText(this, "No s'ha seleccionat el destí", Toast.LENGTH_LONG).show()
                }else {
                    val intent = Intent(this, RutaActivity::class.java)

                    intent.putExtra("origen", adapterOrigen.getSeleccionat())
                    intent.putExtra("desti", adapterDesti.getSeleccionat())
                    intent.putExtra("metode", metode)
                    startActivity(intent)
                }
            }
        }
    }
}