package com.example.activitat6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.activitat6.databinding.FragmentPregunta5Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [Pregunta5.newInstance] factory method to
 * create an instance of this fragment.
 */
class Pregunta5 : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentPregunta5Binding
    //private lateinit var pre: Pregunta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPregunta5Binding.inflate(inflater,container,false)
       // val args = arguments
        //pre = args?.getSerializable("pre") as Pregunta

        val url1 = "https://www.ventos.site/wp-content/uploads/2020/08/Zontes-R-310-1024x682-1-758x576.jpg"
        val url2 = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/Mohn_1413_LRC.jpg/800px-Mohn_1413_LRC.jpg"
        val url3 = "https://noticias.coches.com/wp-content/uploads/2022/10/Jaguar-F-Type-75-10.jpeg"

        Glide.with(this)
                .load(url1)
                .into(binding.ivURL1)
        Glide.with(this)
                .load(url2)
                .into(binding.ivURL2)
        Glide.with(this)
            .load(url3)
            .into(binding.ivURL3)

        binding.tvPre1.setText(preguntes[4].pregunta)
        binding.tvRes1.setText(preguntes[4].resposta1)
        binding.tvRes2.setText(preguntes[4].resposta2)
        binding.tvRes3.setText(preguntes[4].resposta3)

        binding.bRes1.setOnClickListener { preguntes[4].respostaUsuari = 1 }
        binding.bRes2.setOnClickListener { preguntes[4].respostaUsuari = 2 }
        binding.bRes3.setOnClickListener { preguntes[4].respostaUsuari = 3 }

        return binding.root
    }

    fun getRes():Int?{
        if(preguntes[4].respostaUsuari == null){
            return 0
        }else{
            return preguntes[4].respostaUsuari
        }
    }
}