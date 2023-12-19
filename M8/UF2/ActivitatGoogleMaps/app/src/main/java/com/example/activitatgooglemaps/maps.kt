package com.example.activitatgooglemaps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.activitatgooglemaps.BD.BD
import com.example.activitatgooglemaps.BD.Ubicacio
import com.example.activitatgooglemaps.api.CrudAPI
import com.example.activitatgooglemaps.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class maps : Fragment(), OnMapReadyCallback,
    CoroutineScope {

private lateinit var binding: FragmentMapsBinding
    private lateinit var db: BD
    private var job: Job = Job()
    private var ubicacions: ArrayList<Ubicacio> = arrayListOf()
    private lateinit var mMap: GoogleMap
    private var permisosGarantits: Boolean = false
    val locationRequestCode = 0

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapsBinding.inflate(inflater,container, false)





        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return binding.root
    }

    override fun onMapReady(map: GoogleMap) {
        val crudAPI = CrudAPI()
        val carlesvallbona = LatLng(41.60074853389632, 2.283105702292391)
        val hospitalGranollers = LatLng(41.613343,2.294151)
        val placaPorxada = LatLng(41.608041,2.287616)

        map!!.addMarker(
            MarkerOptions().position(hospitalGranollers).title("Hospital General de Granollers")
        )
        map!!.addMarker(
            MarkerOptions().position(carlesvallbona).title("Institut Carles Vallbona")
        )
        map!!.animateCamera(
            CameraUpdateFactory.newLatLngZoom(placaPorxada, 15.0f),
            3000, null
        )

        launch {
            val hospitalGranollersString =
                hospitalGranollers.longitude.toString() + ", " + hospitalGranollers.latitude.toString()
            val carlesvallbonaString =
                carlesvallbona.longitude.toString() + ", " + carlesvallbona.latitude.toString()
            var resposta = crudAPI.getRutaCotxe(hospitalGranollersString, carlesvallbonaString)
            if (resposta != null) {
                drawRoute(map, resposta)
            }
        }
    }

    private fun drawRoute(map: GoogleMap, coordenades: List<List<Double>>) {
        val polyLineOptions = PolylineOptions()
        coordenades.forEach {
            polyLineOptions.add(LatLng(it[1], it[0]))
        }
        requireActivity().runOnUiThread {
            val poly = map.addPolyline(polyLineOptions)
        }
    }



}