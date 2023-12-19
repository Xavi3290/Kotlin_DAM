package com.example.provaretrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.provaretrofit.databinding.FragmentNavSmartphonesBinding


class nav_smartphones : Fragment() {

    private lateinit var binding:FragmentNavSmartphonesBinding
    private lateinit var listTipusProducte:ArrayList<Producte>
    private lateinit var adapter: AdaptadorProducte

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavSmartphonesBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Smartphones"

        listTipusProducte = arrayListOf()
        for (i in 0..listProducte.size - 1) {
            if (2== listProducte[i].tipusProducte) {
                listTipusProducte.add(listProducte[i])
            }
        }

        // emetre notificacions
        val prods = listTipusProducte.filter {
                producte -> producte.tipusCardview == 2
        } as ArrayList<Producte>

        val notificacio = Notificacio(binding.rv1.context)

        var requestCode = 0
        for (prod in prods) {
            notificacio.generaNoticacioText("Especial", prod.descripcio, prod.descripcioCompleta,requestCode)
            requestCode++
        }

        adapter = AdaptadorProducte(listTipusProducte)
        binding.rv1.adapter = adapter
        binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)

        adapter.updateDades(listTipusProducte)

        return binding.root
    }


}