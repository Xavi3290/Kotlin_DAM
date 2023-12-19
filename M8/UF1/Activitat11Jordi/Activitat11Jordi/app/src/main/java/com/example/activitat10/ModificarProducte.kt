package com.example.activitat10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import com.example.activitat10.API.CrudApi
import com.example.activitat10.databinding.FragmentModificarProducteBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ModificarProducte : Fragment(),  AdapterView.OnItemSelectedListener {
    private lateinit var binding : FragmentModificarProducteBinding
    private lateinit var codis: ArrayList<String>
    var codi = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModificarProducteBinding.inflate(inflater, container, false)
        codis = arrayListOf()
        for (prod in llistaProductes){
            codis.add(prod.codi.toString())
        }

        val adapterSpinner = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, codis)
        binding.CodiModificar.adapter = adapterSpinner
        binding.CodiModificar.onItemSelectedListener = this

        binding.btModificar.setOnClickListener {
            val codi = binding.CodiModificar.selectedItem.toString().toInt()
            val descripcio = binding.descModificarProducte.text.toString()
            val descripciocompleta = binding.descModificarCompleta.text.toString()
            val preu = binding.preuModificar.text.toString().toDouble()
            val imatge = binding.imatgeModificar.text.toString()
            val idtipusproducte = binding.rgTipusProducte.checkedRadioButtonId
            val tipusProducte = when (idtipusproducte) {
                R.id.tipusOrdinador -> 1
                R.id.tipusSmartphone -> 0
                R.id.tipusTV -> 2
                else -> 3
            }
            val idtipuscardview = binding.rgTipusCardView.checkedRadioButtonId
            val tipusCardView = when (idtipuscardview) {
                R.id.tipusCVNormal -> 1
                R.id.tipusCVOferta -> 2
                else -> 3
            }
            if (imatge.isEmpty() || descripcio.isEmpty() || descripciocompleta.isEmpty()) {
                Toast.makeText(requireContext(), "Hi ha camps buits", Toast.LENGTH_LONG).show()
            } else {
                val producte = Producte(
                    codi,
                    imatge,
                    descripcio,
                    descripciocompleta,
                    preu,
                    tipusProducte,
                    tipusCardView
                )
                var resposta: Boolean? = null

                runBlocking {
                    val crudApi = CrudApi()

                    val corrutina = launch {
                        resposta = crudApi.modifyProducteFromApi(producte)
                    }
                    corrutina.join()
                }
                if (resposta!!) {
                    Toast.makeText(
                        requireContext(),
                        "Producte " + descripcio + " modificat",
                        Toast.LENGTH_LONG
                    ).show()
                    llistaProductes.add(producte)
                }

            }
        }
        return binding.root

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        this.codi = codis[position].toInt()
        val prod = llistaProductes.filter {
                producte -> producte.codi!!.equals(codi)
        } as ArrayList<Producte>

        binding.preuModificar.setText(prod[0].preu.toString())
        binding.descModificarProducte.setText(prod[0].descripcio)
        binding.imatgeModificar.setText(prod[0].imatge)
        binding.descModificarCompleta.setText(prod[0].descripcioCompleta)
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