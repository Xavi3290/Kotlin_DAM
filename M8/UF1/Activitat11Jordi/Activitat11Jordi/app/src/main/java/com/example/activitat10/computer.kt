package com.example.activitat10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat10.databinding.FragmentComputerBinding


class computer : Fragment() {
    private lateinit var binding: FragmentComputerBinding
    private lateinit var adapter: ProductesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentComputerBinding.inflate(inflater, container, false)


        val llistaComputers = llistaProductes.filter {
                producte -> producte.tipusProducte == 1
        } as ArrayList<Producte>

        binding.filtre.addTextChangedListener { filtre ->
            val novallista = llistaComputers.filter {
                    producte -> producte.descripcio.lowercase().contains(filtre.toString().lowercase())
            } as ArrayList<Producte>
            adapter.updateLlista(novallista)
        }


        binding.rvproductes.layoutManager = LinearLayoutManager(container?.context)
        adapter = ProductesAdapter(llistaComputers)
        binding.rvproductes.adapter = adapter

        // Inflate the layout for this fragment
        return binding.root
    }




}