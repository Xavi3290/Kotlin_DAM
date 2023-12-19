package com.example.pr2xavierroca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pr2xavierroca.BD.BD
import com.example.pr2xavierroca.BD.Serie
import com.example.pr2xavierroca.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.util.ArrayList
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivityMainBinding
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = BD.getDatabase(applicationContext)

       runBlocking {
           val corrutina = launch {
               listSeries = db.daoSerie().selectSeries() as ArrayList<Serie>
           }
           corrutina.join()
       }

        binding.bAfegir.setOnClickListener {
            var serieAf = Serie(null,binding.etTitol.text.toString(),binding.etGenere.text.toString(),binding.etNumTemp.text.toString().toInt())
            runBlocking {
                val corrutina = launch {
                    db.daoSerie().afegirSerie(serieAf)
                }
                corrutina.join()
            }
        }

        binding.bModificar.setOnClickListener {
            var serieMod = Serie(null,binding.etTitol.text.toString(),binding.etGenere.text.toString(),binding.etNumTemp.text.toString().toInt())
            runBlocking {
                val corrutina = launch {
                    db.daoSerie().modificarSerie(serieMod)
                }
                corrutina.join()
            }
        }






    }
    private fun llencarSeries() {
        binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)
        binding.rv1.adapter = AdaptadorSerie(listSeries,coroutineContext)
    }
}