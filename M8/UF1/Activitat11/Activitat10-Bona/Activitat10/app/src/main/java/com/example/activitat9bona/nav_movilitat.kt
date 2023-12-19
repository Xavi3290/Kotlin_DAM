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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class nav_movilitat : Fragment() {
    private lateinit var binding: FragmentNavMovilitatBinding
    private lateinit var adapter: AdaptadorProducte
    private lateinit var listTipusProducte: java.util.ArrayList<Producte>
    private lateinit var listTipusProducteJson: java.util.ArrayList<Producte_JsonItem>
    private lateinit var adapterJson: AdaptadorProducteJson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavMovilitatBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "Movilitat"

        val notificacio = Notificacio(binding.tvFiltrar.context)

        if(userBD!![0].tipus == 0){
            notificacio.generaNoticacioText("Especial", "Patinete eléctrico - SmartGyro Rockway", "Patinete eléctrico - SmartGyro Rockway, 500 W, Hasta 120 Kg, 45 km, 3 velocidades, Negro")
            notificacio.generaNoticacioText("Especial", "Patinete eléctrico - SmartGyro Rockway", "Patinete eléctrico - SmartGyro Rockway, 500 W, Hasta 120 Kg, 45 km, 3 velocidades, Negro")
        }

       /* omplirProductes()

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

        binding.tvFiltrar.addTextChangedListener { filtre ->
            val novesdades = listTipusProducte.filter {
                    Producte ->
                Producte.descripcio.toLowerCase().contains(filtre.toString().toLowerCase())
            } as ArrayList<Producte>
            adapter.updateDades(novesdades)
        }*/


        getMethod()

        binding.tvFiltrar.addTextChangedListener { filtre ->
            val novesdades = listTipusProducteJson.filter {
                    Producte ->
                Producte.descripcio.toLowerCase().contains(filtre.toString().toLowerCase())
            } as ArrayList<Producte_JsonItem>
            adapterJson.updateDades(novesdades)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://private-bb8c36-proves3.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getMethod() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(APIService::class.java).getProductesApi().body()
            //val nomPro = response!!

            listTipusProducteJson = arrayListOf()
            for (i in 0..response!!.size - 1) {
                if (4== response[i].tipusProducte) {
                    listTipusProducteJson.add(response[i])
                }
            }

            requireActivity().runOnUiThread {
                val adaptador = AdaptadorProducteJson(listTipusProducteJson)
                binding.rv1.adapter = adaptador
                binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)
            }
        }

    }


}