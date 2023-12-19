package com.example.activitatgooglemaps


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activitatgooglemaps.databinding.ActivityRutaBinding
import com.google.android.gms.maps.SupportMapFragment


class Ruta : AppCompatActivity() {

    private lateinit var binding: ActivityRutaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRutaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bPrimeraActivity.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

       /* val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment = maps()
        val args = Bundle()

        fragment.arguments = args
        transaction.replace(R.id.map, fragment)
        transaction.commit()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

*/
    }



}