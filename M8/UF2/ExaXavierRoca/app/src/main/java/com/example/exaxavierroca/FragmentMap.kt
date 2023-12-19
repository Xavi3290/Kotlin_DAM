package com.example.exaxavierroca

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.exaxavierroca.api.CrudAPI
import com.example.exaxavierroca.api.RespostaNeta
import com.example.exaxavierroca.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.maps.model.RoundCap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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

        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.googlemap) as SupportMapFragment?

        supportMapFragment!!.getMapAsync(this)



        return binding.root
    }

    override fun onMapReady(map: GoogleMap) {
        val origen = LatLng(listUbicacions[origenPos!!].latitud, listUbicacions[origenPos!!].longitud)


        map!!.addMarker(
            MarkerOptions().position(origen).title("Ubicacio")
        )


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