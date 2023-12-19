package com.example.activitat5maps

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.activitat5maps.bd.dades
import com.example.activitat5maps.databinding.ActivityRutaBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class RutaActivity : AppCompatActivity() {
    lateinit var binding: ActivityRutaBinding
    var fragment: FragmentMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRutaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val origen = intent.getIntExtra("origen",0)
        val desti = intent.getIntExtra("desti", 0)
        val metode = intent.getStringExtra("metode")

        fragment = FragmentMap()

        val args = Bundle()
        args.putInt("origen", origen)
        args.putInt("desti", desti)
        args.putString("metode", metode)
        fragment!!.arguments = args

        supportFragmentManager
            .beginTransaction().replace(R.id.fcview, fragment!!)
            .commit()

        var nomRuta = "Anar de "+dades[origen!!].ubicacio+" a "+dades[desti!!].ubicacio
        if (metode == "Cotxe"){
            nomRuta += " en cotxe"
        }else {
            nomRuta += " a peu"
        }
        binding.tvnomruta.setText(nomRuta)


    }


}