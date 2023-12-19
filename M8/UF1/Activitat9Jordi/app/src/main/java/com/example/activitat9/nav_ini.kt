package com.example.activitat9

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat9.databinding.FragmentNavIniBinding

class nav_ini : Fragment() {
    private lateinit var binding: FragmentNavIniBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavIniBinding.inflate(inflater, container, false)
        binding.rvportada.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvportada.adapter = PortadaAdapter(dadesPortada)

        // Inflate the layout for this fragment
        return binding.root
    }

}