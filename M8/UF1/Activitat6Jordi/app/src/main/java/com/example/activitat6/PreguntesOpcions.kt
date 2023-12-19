package com.example.activitat6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.activitat6.databinding.FragmentPreguntesOpcionsBinding

class PreguntesOpcions : Fragment() {
    private lateinit var binding: FragmentPreguntesOpcionsBinding
    var resposta: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreguntesOpcionsBinding.inflate(inflater, container, false)
        val texte = arguments?.getString("pregunta")
        binding.preguntaopcions.setText(texte)
        binding.opcio1.setText(arguments?.getString("opcio1"))
        binding.opcio2.setText(arguments?.getString("opcio2"))
        binding.opcio3.setText(arguments?.getString("opcio3"))

        resposta = arguments?.getString("resposta")
        if (resposta != null) {
            when (resposta){
                binding.opcio1.text.toString() -> {
                    binding.opcio1.isChecked = true
                }
                binding.opcio2.text.toString() -> {
                    binding.opcio2.isChecked = true
                }
                binding.opcio3.text.toString() -> {
                    binding.opcio3.isChecked = true
                }
            }
        }
        binding.rgopcions.setOnCheckedChangeListener { radioGroup, i ->
            val radi = view?.findViewById<RadioButton>(i)
            resposta = radi!!.text.toString()
        }
        return binding.root
    }


}