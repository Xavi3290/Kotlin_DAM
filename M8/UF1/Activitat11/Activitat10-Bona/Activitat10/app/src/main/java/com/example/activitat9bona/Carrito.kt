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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        /*runBlocking {
            val corrutina = launch{
                db = BD.getDatabase(applicationContext)
                listCarritoBD = db.daoCarrito().selectCarritos(userBD!!.get(0).codi)
            }
            corrutina.join()
        }*/


       // llencarCarrito()

        val codi = intent.getIntExtra("codi", 0)

        if(afegit == true){
            if(userBD!![0].tipus == 0){
                getMethod(codi)
                afegit = false
            }
        }


    }
    private fun llencarCarrito() {
        binding.rv1.layoutManager = LinearLayoutManager(this)
        binding.rv1.adapter = AdaptadorCarritoJson(listCarritoJson,coroutineContext)
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

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://private-bb8c36-proves3.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getMethod(codi:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(APIService::class.java).getProducteApi(codi).body()
            // val nomPro = response!!.descripcio
            listCarritoJson.add(response!!)
             runOnUiThread {
                 val adaptador = AdaptadorCarritoJson(listCarritoJson,coroutineContext)
                 binding.rv1.adapter = adaptador
                 binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)
             }
        }

    }

}