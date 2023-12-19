package com.example.activitat5maps


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.activitat5maps.api.CrudAPI
import com.example.activitat5maps.api.Resposta
import com.example.activitat5maps.api.RespostaNeta
import com.example.activitat5maps.bd.Ubicacio
import com.example.activitat5maps.bd.dades
import com.example.activitat5maps.bd.database
import com.example.activitat5maps.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.maps.model.RoundCap
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class FragmentMap : Fragment(), OnMapReadyCallback, CoroutineScope {
    private lateinit var binding: FragmentMapBinding
    private var job: Job = Job()
    var origenPos: Int? = null
    var destiPos: Int? = null
    var metode : String? = null
    val crudAPI = CrudAPI()
    var resposta : RespostaNeta? = null
    var container: ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        this.container = container

        origenPos = arguments?.getInt("origen")
        destiPos = arguments?.getInt("desti")
        metode = arguments?.getString("metode")



        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.googlemap) as SupportMapFragment?

        supportMapFragment!!.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(map: GoogleMap) {
        val origen = LatLng(dades[origenPos!!].latitud, dades[origenPos!!].longitud)
        val desti = LatLng(dades[destiPos!!].latitud, dades[destiPos!!].longitud)

        map!!.addMarker(
            MarkerOptions().position(origen).title(dades[origenPos!!].ubicacio)
        )
        map!!.addMarker(
            MarkerOptions().position(desti).title(dades[destiPos!!].ubicacio)
        )


        launch {
            val origenString =
                origen.longitude.toString() + ", " + origen.latitude.toString()
            val destiString =
                desti.longitude.toString() + ", " + desti.latitude.toString()

            if (metode == "Cotxe")
                resposta = crudAPI.getRutaCotxe(origenString, destiString)
            else
                resposta = crudAPI.getRutaCaminant(origenString, destiString)

            if (resposta != null) {
                drawRoute(map, resposta!!.coordinates)
                requireActivity().findViewById<TextView>(R.id.tvdistancia).setText("Distància:"+resposta!!.distance.toString()+" metres")
                requireActivity().findViewById<TextView>(R.id.tvtemps).setText("Temps: "+resposta!!.duration.toString()+" segons")
                val puntmig = LatLng((origen.latitude+desti.latitude)/2, (origen.longitude+desti.longitude)/2)
                var zoom : Float? = null
                if (resposta!!.distance < 1000.0)
                    zoom = 15.0f
                else if (resposta!!.distance<= 5000.0)
                    zoom = 14.0f
                else if (resposta!!.distance<= 10000.0)
                    zoom = 13.0f
                else if (resposta!!.distance<= 15000.0)
                    zoom = 12.0f
                else
                    zoom = 11.0f

                map!!.animateCamera(

                    CameraUpdateFactory.newLatLngZoom(puntmig, zoom),
                    3000, null
                )
            }
        }

    }

    private fun drawRoute(map: GoogleMap, coordenades: List<List<Double>>?) {
        val polyLineOptions = PolylineOptions()
        coordenades!!.forEach {
            polyLineOptions.add(LatLng(it[1], it[0]))
        }
        polyLineOptions.color(Color.BLUE)
        polyLineOptions.endCap(RoundCap())
        polyLineOptions.width(6.0f)

        val poly = map.addPolyline(polyLineOptions)


    }

    fun getDistancia(): Double? {
        return resposta!!.distance
    }

    fun getTemps(): Double? {
        return resposta!!.duration
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}