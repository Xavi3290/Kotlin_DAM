package com.example.provaexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.provaexamen.BD.*
import com.example.provaexamen.databinding.ActivityLlistaUbicacioBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LlistaUbicacioActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var binding:ActivityLlistaUbicacioBinding
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
        binding = ActivityLlistaUbicacioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listseleccio= arrayListOf()
        cont = 0

        db = BD.getDatabase(this)

        runBlocking {
            val corrutina = launch {
                listubicacions = db.daoubicacions().selectAll() as ArrayList<Ubicacio>
            }
            corrutina.join()
        }

        binding.rv1.adapter = AdaptadorUbicacions(listubicacions)
        binding.rv1.layoutManager = LinearLayoutManager(this)

        binding.bCotxe.setOnClickListener {
            metode = "Cotxe"
            val intent = Intent(this, RutaActivity::class.java)
            startActivity(intent)
        }

        binding.bCaminant.setOnClickListener {
            metode = "Peu"
            val intent = Intent(this, RutaActivity::class.java)
            startActivity(intent)
        }

    }
}