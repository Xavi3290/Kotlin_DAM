package com.example.activitatgooglemaps

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.activitatgooglemaps.BD.BD
import com.example.activitatgooglemaps.BD.Ubicacio
import com.example.activitatgooglemaps.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope {
    private  var permisosGarantits = false
    val locationRequestCode = 0
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: BD
    private var job: Job = Job()
    private var comprobar = false


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        demanarPermisos()

        binding.bSegonaActivity.setOnClickListener {
            val intent = Intent(this,LlistaActivity::class.java)
            startActivity(intent)
        }

        binding.bGuardar.setOnClickListener {
            comprobar = true
            comprovarPermisos()
            comprobar = false
        }

    }

    fun demanarPermisos() {
        if (
            (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) &&
            (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {
            permisosGarantits = true
            var ubica = ""
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    ubica = "La teva ubicació és ${location!!.latitude},${location!!.longitude}"
                    binding.tvUbicacio.setText(ubica)
                }


        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                Toast.makeText(
                    this,
                    "El permís ACCESS_FINE_LOCATION no està disponible",
                    Toast.LENGTH_LONG
                ).show()
                permisosGarantits = false
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                ) {
                    Toast.makeText(
                        this,
                        "El permís ACCESS_COARSE_LOCATION no està disponible",
                        Toast.LENGTH_LONG
                    ).show()
                    permisosGarantits = false
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ),
                        locationRequestCode
                    )
                }
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (locationRequestCode == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                comprovarPermisos()
            } else
                permisosGarantits = false
        }
    }

    fun comprovarPermisos() {
        if (
            (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) &&
            (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {
            permisosGarantits = true

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    val ubi = "La teva ubicació és ${location!!.latitude},${location!!.longitude}"
                    binding.tvUbicacio.setText(ubi)
                }

            if(comprobar == true){

                db = BD.getDatabase(applicationContext)

                if (binding.etDescripcio.text.isEmpty()) {
                    Toast.makeText(this, "Has de posar descripcio", Toast.LENGTH_LONG).show()
                } else {
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location: Location? ->
                            runBlocking {
                                val corrutina = launch {
                                    var ubi = Ubicacio(
                                        null,
                                        binding.etDescripcio.text.toString(),
                                        location!!.latitude,
                                        location!!.longitude
                                    )
                                    db.daoUbicacio().afegir(ubi)
                                    Toast.makeText(
                                        binding.bSegonaActivity.context,
                                        "Ubicacio guardada",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                                corrutina.join()
                            }
                        }
                }
            }

            }
        }

}