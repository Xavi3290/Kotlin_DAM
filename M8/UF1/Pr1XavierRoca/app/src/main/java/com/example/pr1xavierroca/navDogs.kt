package com.example.pr1xavierroca

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.pr1xavierroca.databinding.FragmentNavDogsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [navDogs.newInstance] factory method to
 * create an instance of this fragment.
 */
class navDogs : Fragment() {
    // TODO: Rename and change types of parameters
   private lateinit var binding: FragmentNavDogsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNavDogsBinding.inflate(inflater,container,false)

        val url1 = "https://s1.abcstatics.com/media/ciencia/2022/04/28/AdobeStock_293422760-kX2H--1248x698@abc.jpg"
        val url2 = "https://ichef.bbci.co.uk/news/800/cpsprodpb/15665/production/_107435678_perro1.jpg.webp"

        Glide.with(this)
            .load(url1)
            .into(binding.ivURL1)
        Glide.with(this)
            .load(url2)
            .into(binding.ivURL2)

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment navDogs.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            navDogs().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}