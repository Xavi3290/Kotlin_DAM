package com.example.activitat10

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.activitat10.API.CrudApi
import com.example.activitat10.databinding.FragmentEsborrarProducteBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class EsborrarProducte : Fragment(),  AdapterView.OnItemSelectedListener {
    private lateinit var binding : FragmentEsborrarProducteBinding
    private lateinit var codis: ArrayList<String>
    private lateinit var adapterSpinner : ArrayAdapter<String>
    var codi = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEsborrarProducteBinding.inflate(inflater, container, false)

        GeneraLlistaCodis()
        adapterSpinner = ArrayAdapter<String>(requireContext(), R.layout.simple_spinner_item, codis)
        binding.CodiEsborrar.adapter = adapterSpinner
        binding.CodiEsborrar.onItemSelectedListener = this

        binding.btEsborrar.setOnClickListener {
            val codi = binding.CodiEsborrar.selectedItem.toString().toInt()
            val descripcio = binding.descEsborrarProducte.text.toString()

            var resposta : Boolean? = null

            runBlocking {
                val crudApi = CrudApi()

                val corrutina = launch {
                    resposta = crudApi.esborraProducteAPI(codi)
                }
                corrutina.join()
            }
            if (resposta!!){
                Toast.makeText(
                    requireContext(),
                    "Producte "+descripcio+" esborrat",
                    Toast.LENGTH_LONG
                ).show()
                val prod = llistaProductes.filter {
                        producte -> producte.codi!!.equals(codi)
                } as ArrayList<Producte>
                llistaProductes.remove(prod[0])

                GeneraLlistaCodis()
            }


        }


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun GeneraLlistaCodis(): ArrayList<String> {
        codis = arrayListOf()
        codis.removeAll(codis)

        adapterSpinner = ArrayAdapter<String>(requireContext(), R.layout.simple_spinner_item, codis)
        binding.CodiEsborrar.adapter = adapterSpinner

        for (prod in llistaProductes){
            codis.add(prod.codi.toString())
        }
        return codis
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        this.codi = codis[position].toInt()
        val prod = llistaProductes.filter {
                producte -> producte.codi!!.equals(codi)
        } as ArrayList<Producte>

        binding.preuEsborrar.setText(prod[0].preu.toString())
        binding.descEsborrarProducte.setText(prod[0].descripcio)
        binding.imatgeEsborrar.setText(prod[0].imatge)
        binding.descEsborrarCompleta.setText(prod[0].descripcioCompleta)
        when (prod[0].tipusProducte){
            0 -> binding.tipusSmartphone.isChecked = true
            1 -> binding.tipusOrdinador.isChecked = true
            2 -> binding.tipusTV.isChecked = true
            else -> binding.tipusMobilitat.isChecked = true
        }
        when (prod[0].tipusCardview){
            0 -> binding.tipusCVNormal.isChecked = true
            1 -> binding.tipusCVOferta.isChecked = true
            else -> binding.tipusCVEspecial.isChecked = true
        }


    }


    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}