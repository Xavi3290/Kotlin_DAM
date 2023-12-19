package com.example.activitatmediarecorderbona

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitatmediarecorderbona.BD.BD
import com.example.activitatmediarecorderbona.BD.Grabacio
import com.example.activitatmediarecorderbona.databinding.FragmentNavLlistaBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class nav_llista : Fragment(), CoroutineScope {

    private lateinit var binding:FragmentNavLlistaBinding
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

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavLlistaBinding.inflate(inflater,container,false)

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



        return binding.root
    }


}