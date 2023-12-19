package com.example.activitat9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
                Producte.descripcio.toLowerCase().contains(filtre.toString().toLowerCase())
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater.inflate(R.menu.optionmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when{
            item.itemId == R.id.Carrito ->{
                val intent = Intent(this, Carrito::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}