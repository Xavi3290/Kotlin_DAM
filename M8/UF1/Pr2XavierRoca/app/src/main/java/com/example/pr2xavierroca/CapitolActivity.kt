package com.example.pr2xavierroca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pr2xavierroca.BD.BD
import com.example.pr2xavierroca.BD.Capitol
import com.example.pr2xavierroca.databinding.ActivityCapitolBinding
import kotlinx.coroutines.*
import java.util.ArrayList
import kotlin.coroutines.CoroutineContext

class CapitolActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var binding:ActivityCapitolBinding
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
        binding = ActivityCapitolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = BD.getDatabase(applicationContext)

        val codiSe = intent.getStringExtra("codi").toString()

        runBlocking {
            val corrutina = launch {
                listCapitols = db.daoCapitol().selectCapitols() as ArrayList<Capitol>
            }
            corrutina.join()
        }

        binding.bAfegir.setOnClickListener {

            if(binding.etImportant.text.toString().toInt() == 0 || binding.etImportant.text.toString().toInt() == 1){
                var capAf = Capitol(null,binding.etTitol.text.toString(),binding.etNumTemp.text.toString().toInt(),
                    binding.etImportant.text.toString().toInt(),binding.etDurada.text.toString().toInt(),codiSe.toInt())
                runBlocking {
                    val corrutina = launch {
                        db.daoCapitol().afegirCapitol(capAf)
                    }
                    corrutina.join()
                }

            }else{
                Toast.makeText(this,"Te que ser 0 o 1", Toast.LENGTH_LONG).show()
            }

        }



    }
    private fun llencarSeries() {
        binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)
        binding.rv1.adapter = AdaptadorCapitol(listCapitols,coroutineContext)
    }
}