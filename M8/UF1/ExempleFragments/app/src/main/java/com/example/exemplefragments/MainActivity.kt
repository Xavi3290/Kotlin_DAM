package com.example.exemplefragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exemplefragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var preguntes = ArrayList<pregunta>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cambiarFragment1()

        //Com ver una transaccio de fragment entre un altre
        binding.fb1.setOnClickListener{
           cambiarFragment1()
        }

        binding.fb2.setOnClickListener {
            cambiarFragment2()
        }
    }
    fun cambiarFragment1(){
        val fragmentManager = supportFragmentManager        // fragmentManager permet treballar amb fragments
        val transaction = fragmentManager.beginTransaction()        // iniciem la transaccio entre un fragment i un altre dins del container
        val fragment1 = Fragment1()     // Agafem el fragment a canviat
        val args = Bundle()
        args.putString("nom","Dam2 - Fragment1")
        fragment1.arguments = args
        transaction.replace(R.id.fc1, fragment1)    //Remplacen al container el fragment actual pel fragment 1
        //transaction.addToBackStack(null)   // para poder tirar atras desde el la flecha de abajo la izquierda
        transaction.commit()        // executa l'accuó
    }

    fun cambiarFragment2(){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragment2 = Fragment2()
        val args = Bundle()
        args.putString("nom","Dam2 - Fragment2")
        fragment2.arguments = args
        transaction.replace(R.id.fc1, fragment2)
        transaction.commit()
    }
    fun omplirPreguntes(){
        preguntes.add(
            pregunta(
                "Quins tipus de variable tenim en kotlin",
                "Solament variables mutables",
                "solament variables immutables",
                "variables mutables i inmutables",
                3,
                null
            )
        )
    }

}