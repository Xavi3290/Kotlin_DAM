package com.example.pr1xavierroca

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.pr1xavierroca.databinding.FragmentNavCatsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [navCats.newInstance] factory method to
 * create an instance of this fragment.
 */
class navCats : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentNavCatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavCatsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        val url1 = "https://i.pinimg.com/originals/46/37/63/4637632aa50dec67576174f269915e11.png"
        val url2 = "https://cdn.pixabay.com/photo/2017/09/01/00/15/png-2702691_960_720.png"

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
         * @return A new instance of fragment navCats.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            navCats().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}