package com.example.activitat9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat9.databinding.ActivityProducteBinding

class ProducteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProducteBinding
    private lateinit var adapter: AdaptadorProducte
    private lateinit var listTipusProducte: java.util.ArrayList<Producte>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProducteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var tipusProduc = intent.getIntExtra("tipusproducte",0)

        omplirProductes()

        binding.tvFiltrar.addTextChangedListener { filtre ->
            val novesdades = listProducte.filter {
                    Producte ->
                Producte.descripcio.contains(filtre.toString())
            } as ArrayList<Producte>
            adapter.updateDades(novesdades)
        }

        listTipusProducte = arrayListOf()
        for (i in 0..listProducte.size - 1) {
            if (tipusProduc== listProducte[i].tipusProducte) {
                listTipusProducte.add(listProducte[i])
            }
        }


        adapter = AdaptadorProducte(listTipusProducte)
        binding.rv1.adapter = adapter
        binding.rv1.layoutManager = LinearLayoutManager(this)

    }

}