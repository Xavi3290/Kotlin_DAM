package com.example.activitat9bona

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.activitat9bona.BD.BD
import com.example.activitat9bona.BD.Usuari
import com.example.activitat9bona.databinding.ActivityFitxaProducteBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.example.activitat9bona.BD.Carritos

class FitxaProducte : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivityFitxaProducteBinding
    private lateinit var db: BD
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFitxaProducteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Producte"

        val imatge = intent.getStringExtra("img").toString()
        val descripcio = intent.getStringExtra("descripcio").toString()
        val descripcioCompleta = intent.getStringExtra("descripcioCompleta").toString()
        val preu = intent.getStringExtra("preu").toString()
        val codi = intent.getIntExtra("codi", 0)

        Glide.with(this).load(imatge).into(binding.ivImatge)
        binding.tvDescripcio.setText(descripcio)
        binding.tvDescripcioCompleta.setText(descripcioCompleta)
        binding.tvPreu.setText(preu)

        //listCarrito = arrayListOf()


        binding.bAfegir.setOnClickListener {

            for (i in 0..listProducte.size - 1) {
                if (codi == listProducte[i].codi) {
                    listCarrito.add(listProducte[i])
                    var proCar:Carritos
                    proCar = Carritos(null, userBD!![0].codi, listProducte[i].codi, listProducte[i].imatge,
                        listProducte[i].descripcio, listProducte[i].descripcioCompleta, listProducte[i].preu,
                        listProducte[i].tipusProducte, listProducte[i].tipusCardview)
                    db = BD.getDatabase(applicationContext)
                    runBlocking {
                        val corrutina = launch {
                            //db = BD.getDatabase(applicationContext)
                            db.daoCarrito().afegirCarrito(proCar)
                        }
                        corrutina.join()
                    }
                }
            }

            val intent = Intent(this, Carrito::class.java)
            startActivity(intent)

        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when{
            item.itemId == R.id.Carrito ->{
                val intent = Intent(this, Carrito::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}