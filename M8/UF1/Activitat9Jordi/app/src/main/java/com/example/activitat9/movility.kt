package com.example.activitat9

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat9.databinding.FragmentMovilityBinding

class movility : Fragment() {
    private lateinit var binding: FragmentMovilityBinding
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
        binding = FragmentMovilityBinding.inflate(inflater, container, false)

        val llistaMovility = llistaProductes.filter {
                producte -> producte.tipusProducte == 3
        } as ArrayList<producte>


        binding.filtre.addTextChangedListener { filtre ->
            val novallista = llistaMovility.filter {
                    producte -> producte.descripcio.lowercase().contains(filtre.toString().lowercase())
            } as ArrayList<producte>
            adapter.updateLlista(novallista)
        }

        binding.rvproductes.layoutManager = LinearLayoutManager(container?.context)
        adapter = ProductesAdapter(llistaMovility)
        binding.rvproductes.adapter = adapter

        return binding.root
    }
    
}