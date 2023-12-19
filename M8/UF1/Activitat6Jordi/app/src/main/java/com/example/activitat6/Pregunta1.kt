package com.example.activitat6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activitat6.databinding.FragmentPregunta1Binding


class Pregunta1 : Fragment() {
    private lateinit var binding: FragmentPregunta1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPregunta1Binding.inflate(inflater, container, false)
        val texte = arguments?.getString("pregunta")
        binding.preguntadirecta.setText(texte)
        val resposta = arguments?.getString("resposta")
        if (resposta != null)
            binding.respostadirecta.setText(resposta)
        return binding.root
    }

    fun getResposta(): String {
        return binding.respostadirecta.text.toString()
    }
}