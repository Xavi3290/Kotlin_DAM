package com.example.exemplebd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exemplebd.bd.BdPelicules
import com.example.exemplebd.bd.Pelicula
import com.example.exemplebd.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.util.Date
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    var job = Job()
    private lateinit var binding: ActivityMainBinding
    private lateinit var llista: java.util.ArrayList<Pelicula>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bd = BdPelicules.getDatabase(this)

        runBlocking {
            val corrutina = launch {
                var llista =bd.Dao().selectAllPelicules()
            }
            corrutina.join()
        }

        binding.afegidDades.setOnClickListener {

            var peli1 = Pelicula(null, "Peli 1", "20/10/2022")
            var peli2 = Pelicula(null, "Peli 2", "20/09/2022")

            runBlocking {
                bd.Dao().afegirPelicula(peli1)
                bd.Dao().afegirPelicula(peli2)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }


}