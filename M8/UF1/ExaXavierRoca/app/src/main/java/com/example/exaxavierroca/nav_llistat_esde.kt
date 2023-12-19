package com.example.exaxavierroca

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exaxavierroca.BD.BD
import com.example.exaxavierroca.BD.Esdeveniment
import com.example.exaxavierroca.databinding.FragmentNavLlistatEsdeBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class nav_llistat_esde : Fragment(), CoroutineScope {

    private lateinit var binding:FragmentNavLlistatEsdeBinding
    private lateinit var db: BD
    private var job: Job = Job()
    private lateinit var adapter: AdaptadorEsdeveniments


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavLlistatEsdeBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "Llistat d'Esdeveniment"


        runBlocking {
            val corrutina = launch{
                db = BD.getDatabase(binding.rv1.context)
                listEsdeveniments = db.daoEsdeveniment().selectEsdeveniments() as ArrayList<Esdeveniment>
            }
            corrutina.join()

        }


        val adaptador = AdaptadorEsdeveniments(listEsdeveniments,coroutineContext)
        binding.rv1.adapter = adaptador
        binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)



        return binding.root
    }


}