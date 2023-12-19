package com.example.activitat6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.activitat6.databinding.FragmentPreguntesLogiquesBinding

class PreguntesLogiques : Fragment() {
    private lateinit var binding: FragmentPreguntesLogiquesBinding
    var resposta: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreguntesLogiquesBinding.inflate(inflater, container, false)
        val texte = arguments?.getString("pregunta")
        binding.preguntalogica.setText(texte)

        resposta = arguments?.getString("resposta")
        if (resposta != null) {
            if (resposta == "Cert")
                binding.opcioTrue.isChecked = true
            else
                binding.opcioFalse.isChecked = true
        }

        binding.rgopcions.setOnCheckedChangeListener { radioGroup, i ->
            val radio = view?.findViewById<RadioButton>(i)
            resposta = radio?.text.toString()
        }
        return binding.root
    }

}