package com.example.activitat9bona

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat9bona.BD.BD
import com.example.activitat9bona.BD.Carritos
import com.example.activitat9bona.databinding.ActivityCarritoBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Carrito : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivityCarritoBinding
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
        binding = ActivityCarritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Carret"

        //omplirProductes()  //aqui no feia falta
       /* runBlocking {
            val corrutina = launch{
                db = BD.getDatabase(applicationContext)
                listCarritoBD = db.daoCarrito().selectCarritos(userBD!!.get(0).codi)
            }
            corrutina.join()
        }*/


        llencarCarrito()


    }
    private fun llencarCarrito() {
        binding.rv1.layoutManager = LinearLayoutManager(this)
        binding.rv1.adapter = AdaptadorCarrito(listCarrito,coroutineContext)
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