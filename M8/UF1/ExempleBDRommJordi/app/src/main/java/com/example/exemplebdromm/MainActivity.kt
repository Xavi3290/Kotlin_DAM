package com.example.exemplebdromm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exemplebdromm.bd.BD
import com.example.exemplebdromm.bd.Departament


import com.example.exemplebdromm.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var db: BD
    private var job: Job = Job()

    private lateinit var binding: ActivityMainBinding
    private lateinit var llistadep : ArrayList<Departament>
    private lateinit var adapterDept: AdapterDept

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

        runBlocking {
            val corrutina = launch {
                db = BD.getDatabase(applicationContext)

                llistadep = db.daoDepartaments().getAllDepartaments() as ArrayList<Departament>

            }
            corrutina.join()
        }
        adapterDept = AdapterDept(llistadep, coroutineContext)
        binding.rvDep.adapter = adapterDept
        binding.rvDep.layoutManager = LinearLayoutManager(binding.rvDep.context)

        binding.afegirDep.setOnClickListener {
            var nouDep = binding.etNouDep.text.toString()
            runBlocking {
                val corrutina = launch {
                    db = BD.getDatabase(applicationContext)
                    var departament = Departament(null, nouDep)
                    db.daoDepartaments().afegeixDepartament(departament)
                    llistadep = db.daoDepartaments().getAllDepartaments() as ArrayList<Departament>
                }
                corrutina.join()
                adapterDept.updateLlista(llistadep)
            }
        }
    }


}