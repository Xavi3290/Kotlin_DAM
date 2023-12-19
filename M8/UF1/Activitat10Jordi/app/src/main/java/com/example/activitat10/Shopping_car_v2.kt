package com.example.activitat10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat10.database.database
import com.example.activitat10.databinding.ActivityShoppingCarV2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class ShoppingCarv2 : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivityShoppingCarV2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCarV2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvShoppingCar.adapter = AdapterShoppingCarV2(llistaCarret)
        binding.rvShoppingCar.layoutManager = LinearLayoutManager(this)

        binding.guardar.setOnClickListener {
            val db = database.getDatabase(applicationContext)
            for (prod in llistaCarret){
                runBlocking {
                    launch {
                        db.DaoProductes().updateProducteCarret(prod)
                    }
                }
            }
            Toast.makeText(this, "Carret actualitzat a la BD", Toast.LENGTH_LONG).show()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = TODO("Not yet implemented")
}