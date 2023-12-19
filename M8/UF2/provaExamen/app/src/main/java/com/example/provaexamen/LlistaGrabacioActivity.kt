package com.example.provaexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.provaexamen.BD.BD
import com.example.provaexamen.BD.Grabacio
import com.example.provaexamen.databinding.ActivityLlistaGrabacioBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LlistaGrabacioActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var binding:ActivityLlistaGrabacioBinding
    private lateinit var listGrabacions: ArrayList<Grabacio>
    private lateinit var db: BD
    private var job: Job = Job()
    private lateinit var adapter: AdaptadorGrabacions



    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLlistaGrabacioBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = BD.getDatabase(binding.rv1.context)
        runBlocking {
            val corrutina = launch{
                listGrabacions = db.daoGrabacions().selectGrabacions() as ArrayList<Grabacio>
            }
            corrutina.join()
        }



        adapter = AdaptadorGrabacions(listGrabacions,coroutineContext)
        binding.rv1.adapter = adapter
        binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)



        binding.bGrabacio.setOnClickListener {
            val intent = Intent(this, GrabacioActivity::class.java)
            startActivity(intent)
        }

        binding.bMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}