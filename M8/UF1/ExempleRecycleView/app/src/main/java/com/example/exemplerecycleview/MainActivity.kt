package com.example.exemplerecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exemplerecycleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var elements: java.util.ArrayList<Element>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        omplirElements()

        llencarLlista()
    }

    private fun llencarLlista() {
        binding.rv1.layoutManager = LinearLayoutManager(this)
        binding.rv1.adapter = Adaptador(elements)
    }


    fun omplirElements(){
        elements = arrayListOf()
        elements.addAll(
            listOf(
                Element(R.drawable.android_image_1,"Android 1"),
                Element(R.drawable.android_image_2,"Android 2"),
                Element(R.drawable.android_image_3,"Android 3"),
                Element(R.drawable.android_image_4,"Android 4"),
                Element(R.drawable.android_image_5,"Android 5"),
                Element(R.drawable.android_image_6,"Android 6"),
                Element(R.drawable.android_image_7,"Android 7"),
                Element(R.drawable.android_image_8,"Android 8")

            )

        )
    }
}