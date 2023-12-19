package com.example.provaretrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.provaretrofit.API.CrudApi
import com.example.provaretrofit.databinding.FragmentNavModificarBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class nav_modificar : Fragment() {

    private lateinit var binding:FragmentNavModificarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavModificarBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Modificar"

        binding.bAfegir.setOnClickListener {                 //Hacerlo afegir
            //val codi = binding.etCodi.text.toString().toInt()
            val descripcio = binding.etDescripcio.text.toString()
            val descripcioComp = binding.etDescripcioCom.text.toString()
            val preu = binding.etPreu.text.toString().toDouble()
            val imatge = binding.etImatge.text.toString()
            val tipusproduc = binding.etTpProducte.text.toString().toInt()
            val tipuscard = binding.etTpCard.text.toString().toInt()

            if(binding.etDescripcio.text.isNotEmpty() && binding.etDescripcioCom.text.isNotEmpty()
                && binding.etImatge.text.isNotEmpty() && binding.etPreu.text.isNotEmpty() && binding.etTpCard.text.isNotEmpty()
                && binding.etTpProducte.text.isNotEmpty()){

                val producte = Producte(null,imatge,descripcio,descripcioComp,preu,tipusproduc,tipuscard)

                var resposta: Boolean? = null

                runBlocking {
                    val crudApi = CrudApi()

                    val corrutina = launch {
                        resposta = crudApi.addProducteToAPI(producte)
                    }
                    corrutina.join()
                }
                if (resposta!!) {
                    Toast.makeText(
                        requireContext(),
                        "Producte " + descripcio + " afegit",
                        Toast.LENGTH_LONG
                    ).show()
                    listProducte.add(producte)
                }else{
                    Toast.makeText(
                        requireContext(),
                        "Producte no afegit",
                        Toast.LENGTH_LONG
                    ).show()
                }


            }else{
                Toast.makeText(requireContext(), "Hi ha camps buits", Toast.LENGTH_LONG).show()
            }
        }










        return binding.root
    }


}