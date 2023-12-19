package com.example.activitat6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activitat6.databinding.FragmentPregunta3Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [Pregunta3.newInstance] factory method to
 * create an instance of this fragment.
 */
class Pregunta3 : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentPregunta3Binding
    //private lateinit var pre: Pregunta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPregunta3Binding.inflate(inflater,container,false)
       // val args = arguments
        //pre = args?.getSerializable("pre") as Pregunta


        binding.tvPre1.setText(preguntes[2].pregunta)
        binding.tvRes1.setText(preguntes[2].resposta1)
        binding.tvRes2.setText(preguntes[2].resposta2)
        binding.tvRes3.setText(preguntes[2].resposta3)

        binding.bRes1.setOnClickListener { preguntes[2].respostaUsuari = 1 }
        binding.bRes2.setOnClickListener { preguntes[2].respostaUsuari = 2 }
        binding.bRes3.setOnClickListener { preguntes[2].respostaUsuari = 3 }

        return binding.root
    }

    fun getRes():Int?{
        if(preguntes[2].respostaUsuari == null){
            return 0
        }else{
            return preguntes[2].respostaUsuari
        }
    }

}