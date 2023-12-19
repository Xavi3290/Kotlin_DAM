package com.example.exemplerutesgooglemaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.exemplerutesgooglemaps.api.CrudAPI
import com.example.exemplerutesgooglemaps.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), OnMapReadyCallback, CoroutineScope {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mMap: GoogleMap
    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


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
        runOnUiThread {
            val poly = map.addPolyline(polyLineOptions)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}