package com.example.activitat5maps

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.activitat5maps.bd.Ubicacio
import com.example.activitat5maps.bd.database
import com.example.activitat5maps.databinding.ActivityMainBinding
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    val locationRequestCode = 0
    var permisosGarantits = false
    private var job: Job = Job()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: ActivityMainBinding
    var ubi: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        demanarPermisos()



        binding.BTAfegir.setOnClickListener {
            runBlocking {
                val corrutina = launch {
                    val ubi2 = Ubicacio(null, binding.ETNovaLocalitzacio.text.toString(), ubi!!.latitude, ubi!!.longitude, 0)
                    database.getDatabase(this@MainActivity).DaoUbicacions().afegeixUbicacio(ubi2)
                }
                corrutina.join()
                Toast.makeText(this@MainActivity, "Ubicació afegida", Toast.LENGTH_LONG).show()
            }
        }

        binding.BTLlistar.setOnClickListener {
            val intent = Intent(this, ParametresRuta::class.java)
            startActivity(intent)
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
            binding.BTAfegir.isEnabled = true
            ubiActual()
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
                binding.BTAfegir.isEnabled = false
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
                    binding.BTAfegir.isEnabled = false
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
                permisosGarantits = true
                binding.BTAfegir.isEnabled = true
                ubiActual()
            } else {
                permisosGarantits = false
                binding.BTAfegir.isEnabled = false
            }
        }
    }

    fun ubiActual() {

        if (
            (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) &&
            (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {
            fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token

                override fun isCancellationRequested() = false
            })
                .addOnSuccessListener { location: Location? ->
                    if (location == null)
                        Toast.makeText(this, "Cannot get location.", Toast.LENGTH_SHORT).show()
                    else {
                        ubi = location
                        if (ubi != null){
                            binding.tvCoordinades.text = "Latitud: "+ubi!!.latitude+", Longitud: "+ubi!!.longitude

                        }
                    }

                }
        }

    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


}