package com.example.exaxavierroca

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.exaxavierroca.BD.BD
import com.example.exaxavierroca.BD.Esdeveniment
import com.example.exaxavierroca.databinding.FragmentNavAfegirEsdeBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class nav_afegir_esde : Fragment(), CoroutineScope {

    private lateinit var binding:FragmentNavAfegirEsdeBinding
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

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavAfegirEsdeBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "Afegir Esdeveniment"

        binding.bAfegir.setOnClickListener {

            var esdeveniment = Esdeveniment(null,binding.etDescripcio.text.toString(),binding.etData.text.toString())

            runBlocking {
                val corrutina = launch{
                    db = BD.getDatabase(binding.bAfegir.context)
                    db.daoEsdeveniment().afegirEsdeveniment(esdeveniment)
                }
                corrutina.join()
            }
        }



        return binding.root
    }


}