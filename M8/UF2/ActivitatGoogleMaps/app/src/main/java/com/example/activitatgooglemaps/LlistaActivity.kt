package com.example.activitatgooglemaps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitatgooglemaps.BD.BD
import com.example.activitatgooglemaps.BD.Ubicacio
import com.example.activitatgooglemaps.BD.listubicacions
import com.example.activitatgooglemaps.databinding.ActivityLlistaBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LlistaActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var binding:ActivityLlistaBinding

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
        binding = ActivityLlistaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = BD.getDatabase(this)

        runBlocking {
            val corrutina = launch {
                listubicacions = db.daoUbicacio().selectAll() as ArrayList<Ubicacio>
            }
            corrutina.join()
        }


        llencarUbicacions()

        binding.bMapa.setOnClickListener {
            val intent = Intent(this,Ruta::class.java)
            startActivity(intent)
        }

        binding.bTornar.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun llencarUbicacions() {
        binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)
        binding.rv1.adapter = AdaptadorUbicacions(listubicacions)
    }
}