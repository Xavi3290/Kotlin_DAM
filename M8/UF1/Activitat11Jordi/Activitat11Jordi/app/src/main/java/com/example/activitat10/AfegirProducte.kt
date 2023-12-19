package com.example.activitat10

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.activitat10.API.CrudApi
import com.example.activitat10.API.HeaderInterceptor
import com.example.activitat10.API.logging
import com.example.activitat10.databinding.FragmentAfegirProducteBinding
import com.example.activitat10.notification.GlideApp
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.atomic.AtomicInteger

class AfegirProducte : Fragment() {

    private lateinit var binding: FragmentAfegirProducteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAfegirProducteBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.btAfegir.setOnClickListener {
            val descripcio = binding.descAfegirProducte.text.toString()
            val descripciocompleta = binding.descAfegirCompleta.text.toString()
            val preu = binding.preuAfegir.text.toString().toDouble()
            val imatge = binding.imatgeAfegir.text.toString()
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
                val producteAfegir = Producte(
                    null,
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
                        resposta = crudApi.addProducteToAPI(producteAfegir)
                    }
                    corrutina.join()
                }
                if (resposta!!) {
                    Toast.makeText(
                        requireContext(),
                        "Producte " + descripcio + " afegit",
                        Toast.LENGTH_LONG
                    ).show()
                    llistaProductes.add(producteAfegir)
                }

            }
        }

        return binding.root
    }


}