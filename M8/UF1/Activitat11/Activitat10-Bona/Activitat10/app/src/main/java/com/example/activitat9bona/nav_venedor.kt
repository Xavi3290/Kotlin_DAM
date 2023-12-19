package com.example.activitat9bona

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat9bona.BD.BD
import com.example.activitat9bona.databinding.FragmentNavVenedorBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext


class nav_venedor : Fragment(){
    private lateinit var binding: FragmentNavVenedorBinding
    private lateinit var producte:Producte_JsonItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavVenedorBinding.inflate(inflater,container, false)

        (activity as AppCompatActivity).supportActionBar?.hide()



        binding.bAfegir.setOnClickListener{

            if(binding.etCodi.text.isNotEmpty() && binding.etDescripcio.text.isNotEmpty() && binding.etDescripcioCom.text.isNotEmpty()
                && binding.etImatge.text.isNotEmpty() && binding.etPreu.text.isNotEmpty() && binding.etTpCard.text.isNotEmpty()
                && binding.etTpProducte.text.isNotEmpty()){

                producte = Producte_JsonItem(binding.etCodi.text.toString().toInt(),binding.etDescripcio.text.toString(),binding.etDescripcioCom.text.toString(),
                binding.etImatge.text.toString(),binding.etPreu.text.toString().toDouble(),binding.etTpCard.text.toString().toInt(),
                binding.etTpProducte.text.toString().toInt())

                getMethodAfegir(producte)
            }else{
                Toast.makeText(
                    binding.bAfegir.context,
                    "No pot haber apartats buits" ,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        binding.bModificar.setOnClickListener{
            var pro:Producte_JsonItem?
            if(binding.etCodi.text.isNotEmpty()){
                pro = getMethodGetandModificar(binding.etCodi.text.toString().toInt())



                //pro = getMethodGet(binding.etCodi.text.toString().toInt())

               /* if(pro == null){
                    Toast.makeText(
                        binding.bAfegir.context,
                        "No hi ha aquest codi per Modificar" ,
                        Toast.LENGTH_SHORT
                    ).show()
                }else{

                    if(binding.etCodi.text.isNotEmpty() && binding.etDescripcio.text.isNotEmpty() && binding.etDescripcioCom.text.isNotEmpty()
                        && binding.etImatge.text.isNotEmpty() && binding.etPreu.text.isNotEmpty() && binding.etTpCard.text.isNotEmpty()
                        && binding.etTpProducte.text.isNotEmpty()){

                        pro = Producte_JsonItem(binding.etCodi.text.toString().toInt(),binding.etDescripcio.text.toString(),binding.etDescripcioCom.text.toString(),
                            binding.etImatge.text.toString(),binding.etPreu.text.toString().toDouble(),binding.etTpCard.text.toString().toInt(),
                            binding.etTpProducte.text.toString().toInt())

                        getMethodModificar(binding.etCodi.text.toString().toInt(), pro)
                    }

                }*/

            }else{
                Toast.makeText(
                    binding.bAfegir.context,
                    "No pot estar el codi buit" ,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        binding.bEliminar.setOnClickListener {
            var pro:Producte_JsonItem?
            if(binding.etCodi.text.isNotEmpty()){
                pro = getMethodGetandEliminar(binding.etCodi.text.toString().toInt())
               // pro = getMethodGet(binding.etCodi.text.toString().toInt())

               /* if(pro == null){
                    Toast.makeText(
                        binding.bAfegir.context,
                        "No hi ha aquest codi per eliminar" ,
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    getMethodEliminar(binding.etCodi.text.toString().toInt())
                }*/

            }else{
                Toast.makeText(
                    binding.bAfegir.context,
                    "No pot estar el codi buit" ,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }



        return binding.root
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://private-bb8c36-proves3.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getMethodAfegir(producte: Producte_JsonItem) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(APIService::class.java).insertProducte(producte).body()
            val nomPro = response!!.message

            requireActivity().runOnUiThread {

                if(nomPro.equals("Producte creat correctament")){
                    Toast.makeText(
                        binding.bAfegir.context,
                        nomPro ,
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    Toast.makeText(
                        binding.bAfegir.context,
                        "El Producte no s'ha afegit" ,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

        }

    }

    fun getMethodModificar(codi:Int,producte: Producte_JsonItem) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(APIService::class.java).updateProducte(codi,producte).body()
            val nomPro = response!!.message

            requireActivity().runOnUiThread {

                if(nomPro.equals("Producte modificat correctament")){
                    Toast.makeText(
                        binding.bAfegir.context,
                        nomPro ,
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    Toast.makeText(
                        binding.bAfegir.context,
                        "El Producte no s'ha modificat" ,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

        }

    }

    fun getMethodEliminar(codi:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(APIService::class.java).deleteProducte(codi).body()
            val nomPro = response!!.message

            requireActivity().runOnUiThread {

                if(nomPro.equals("Producte borrat correctament")){
                    Toast.makeText(
                        binding.bAfegir.context,
                        nomPro ,
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    Toast.makeText(
                        binding.bAfegir.context,
                        "El Producte no s'ha borrat" ,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }


        }

    }

    fun getMethodGetandEliminar(codi:Int) : Producte_JsonItem?{
        var pro:Producte_JsonItem? = null
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(APIService::class.java).getProductesApi().body()
            //val nomPro = response!!

            for (i in 0..response!!.size - 1) {
                if (codi == response[i].codi) {
                    pro = response[i]

                }
            }
            if(pro == null){
                requireActivity().runOnUiThread {

                    Toast.makeText(
                        binding.bAfegir.context,
                        "No hi ha aquest codi per eliminar" ,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }else{
                getMethodEliminar(binding.etCodi.text.toString().toInt())
            }

        }
        return pro
    }

    fun getMethodGetandModificar(codi:Int) : Producte_JsonItem?{
        var pro:Producte_JsonItem? = null
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(APIService::class.java).getProductesApi().body()
            //val nomPro = response!!

            for (i in 0..response!!.size - 1) {
                if (codi == response[i].codi) {
                    pro = response[i]

                }
            }

            if(pro == null){
                requireActivity().runOnUiThread {
                    Toast.makeText(
                        binding.bAfegir.context,
                        "No hi ha aquest codi per Modificar" ,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }else{

                if(binding.etCodi.text.isNotEmpty() && binding.etDescripcio.text.isNotEmpty() && binding.etDescripcioCom.text.isNotEmpty()
                    && binding.etImatge.text.isNotEmpty() && binding.etPreu.text.isNotEmpty() && binding.etTpCard.text.isNotEmpty()
                    && binding.etTpProducte.text.isNotEmpty()){

                    var prod = Producte_JsonItem(binding.etCodi.text.toString().toInt(),binding.etDescripcio.text.toString(),binding.etDescripcioCom.text.toString(),
                        binding.etImatge.text.toString(),binding.etPreu.text.toString().toDouble(),binding.etTpCard.text.toString().toInt(),
                        binding.etTpProducte.text.toString().toInt())

                    getMethodModificar(binding.etCodi.text.toString().toInt(), prod)
                }

            }

        }
        return pro
    }

    //No va este metodo, no he conseguido sacarlo
    suspend  fun getMethodGet(codi:Int) : Producte_JsonItem?{
        var pro:Producte_JsonItem? = null

            val response = getRetrofit().create(APIService::class.java).getProductesApi().body()
            //val nomPro = response!!


            for (i in 0..response!!.size - 1) {
                    if (codi == response[i].codi) {
                        pro = response[i]

                    }
            }

        return pro
    }


}