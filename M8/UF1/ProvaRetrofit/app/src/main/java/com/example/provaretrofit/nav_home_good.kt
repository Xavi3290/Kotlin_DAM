package com.example.provaretrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.provaretrofit.API.CrudApi
import com.example.provaretrofit.databinding.FragmentNavHomeGoodBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class nav_home_good : Fragment(){

    private lateinit var binding:FragmentNavHomeGoodBinding
    private lateinit var listTipusProducte:ArrayList<Producte>
    private lateinit var adapter: AdaptadorProducte


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavHomeGoodBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "Home"

        runBlocking {                   // si no aparecen las imagenes al principio despues de esperar ponerlo en el primer
            val crudApi = CrudApi()                     // fragmento que se vea
            val corrutina = launch {
                listProducte = crudApi.getAllFromAPI() as ArrayList<Producte>
            }
            corrutina.join()
        }


        listTipusProducte = arrayListOf()
        for (i in 0..listProducte.size - 1) {
            if (1== listProducte[i].tipusProducte) {
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