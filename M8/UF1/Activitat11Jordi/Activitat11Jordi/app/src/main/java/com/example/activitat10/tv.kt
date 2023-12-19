package com.example.activitat10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat10.databinding.FragmentTvBinding

class tv : Fragment() {
    private lateinit var binding: FragmentTvBinding
    private lateinit var adapter: ProductesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvBinding.inflate(inflater, container, false)

        val llistaTVs = llistaProductes.filter {
                producte -> producte.tipusProducte == 2
        } as ArrayList<Producte>

        binding.filtre.addTextChangedListener { filtre ->
            val novallista = llistaTVs.filter {
                    producte -> producte.descripcio.lowercase().contains(filtre.toString().lowercase())
            } as ArrayList<Producte>
            adapter.updateLlista(novallista)
        }

        binding.rvproductes.layoutManager = LinearLayoutManager(container?.context)
        adapter = ProductesAdapter(llistaTVs)
        binding.rvproductes.adapter = adapter

        return binding.root
    }

}