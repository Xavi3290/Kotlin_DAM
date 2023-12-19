package com.example.activitat6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.activitat6.databinding.FragmentPregunta1Binding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [Pregunta1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Pregunta1 : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentPregunta1Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPregunta1Binding.inflate(inflater,container,false)
       // val args = arguments

       // val pre = args?.getString("pregunta")
       // val res1 = args?.getString("resposta1")
        //val res2 = args?.getString("resposta2")
        //val res3 = args?.getString("resposta3")
       // val cor = args?.getInt("correcte")
       // var usu = args?.getInt("respostaUs")

        binding.tvPre1.setText(preguntes[0].pregunta)
        binding.tvRes1.setText(preguntes[0].resposta1)
        binding.tvRes2.setText(preguntes[0].resposta2)
        binding.tvRes3.setText(preguntes[0].resposta3)

        binding.bRes1.setOnClickListener { preguntes[0].respostaUsuari = 1 }
        binding.bRes2.setOnClickListener { preguntes[0].respostaUsuari = 2 }
        binding.bRes3.setOnClickListener { preguntes[0].respostaUsuari = 3 }

        return binding.root
    }

    fun getRes():Int?{
        if(preguntes[0].respostaUsuari == null){
            return 0
        }else{
            return preguntes[0].respostaUsuari
        }

    }

}