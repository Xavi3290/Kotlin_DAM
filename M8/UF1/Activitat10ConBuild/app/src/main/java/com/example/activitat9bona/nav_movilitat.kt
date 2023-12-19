package com.example.activitat9bona

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat9bona.databinding.FragmentNavMovilitatBinding


class nav_movilitat : Fragment() {
    private lateinit var binding: FragmentNavMovilitatBinding
    private lateinit var adapter: AdaptadorProducte
    private lateinit var listTipusProducte: java.util.ArrayList<Producte>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavMovilitatBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "Movilitat"

        omplirProductes()

        listTipusProducte = arrayListOf()
        for (i in 0..listProducte.size - 1) {
            if (4== listProducte[i].tipusProducte) {
                listTipusProducte.add(listProducte[i])
            }
        }

        binding.tvFiltrar.addTextChangedListener { filtre ->
            val novesdades = listTipusProducte.filter {
                    Producte ->
                Producte.descripcio.toLowerCase().contains(filtre.toString().toLowerCase())
            } as ArrayList<Producte>
            adapter.updateDades(novesdades)
        }



        adapter = AdaptadorProducte(listTipusProducte)
        binding.rv1.adapter = adapter
        binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)



        // Inflate the layout for this fragment
        return binding.root
    }


}