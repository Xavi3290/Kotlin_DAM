package com.example.activitat10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat10.databinding.FragmentSmartphonesBinding

class smartphones : Fragment() {
    private lateinit var binding: FragmentSmartphonesBinding
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

        binding = FragmentSmartphonesBinding.inflate(inflater, container, false)

        val llistaSmartphones = llistaProductes.filter {
                producte -> producte.tipusProducte == 0
        } as ArrayList<producte>

        binding.filtre.addTextChangedListener { filtre ->
            val novallista = llistaSmartphones.filter {
                    producte -> producte.descripcio.lowercase().contains(filtre.toString().lowercase())
            } as ArrayList<producte>
            adapter.updateLlista(novallista)
        }

        binding.rvproductes.layoutManager = LinearLayoutManager(container?.context)
        adapter = ProductesAdapter(llistaSmartphones)
        binding.rvproductes.adapter = adapter

        return binding.root
    }

}