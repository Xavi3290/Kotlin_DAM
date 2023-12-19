package com.example.provaexamen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.provaexamen.BD.listseleccio
import com.example.provaexamen.BD.metode
import com.example.provaexamen.databinding.ActivityRutaBinding

class RutaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRutaBinding
    var fragment: FragmentMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRutaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        fragment = FragmentMap()

        val args = Bundle()

        fragment!!.arguments = args

        supportFragmentManager
            .beginTransaction().replace(R.id.fcview, fragment!!)
            .commit()

        var nomRuta = "Anar de "+ listseleccio[0].descripcio+" a "+ listseleccio[1].descripcio
        if (metode == "Cotxe"){
            nomRuta += " en cotxe"
        }else {
            nomRuta += " a peu"
        }
        binding.tvnomruta.setText(nomRuta)



    }
}