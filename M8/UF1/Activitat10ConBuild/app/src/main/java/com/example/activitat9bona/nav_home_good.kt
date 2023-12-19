package com.example.activitat9bona

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat9bona.databinding.FragmentNavHomeGoodBinding


class nav_home_good : Fragment() {

    private lateinit var binding: FragmentNavHomeGoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavHomeGoodBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "Home"

        omplirPortada()

        llencarPortada()




        // Inflate the layout for this fragment
        return binding.root
    }

    private fun llencarPortada() {
        binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)
        binding.rv1.adapter = AdaptadorPortada(listPortada)
    }


}