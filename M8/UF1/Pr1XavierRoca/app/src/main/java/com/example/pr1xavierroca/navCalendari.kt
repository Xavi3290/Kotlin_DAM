package com.example.pr1xavierroca

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pr1xavierroca.databinding.FragmentNavCalendariBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [navCalendari.newInstance] factory method to
 * create an instance of this fragment.
 */
class navCalendari : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentNavCalendariBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNavCalendariBinding.inflate(inflater, container, false)

        binding.bEnviar.setOnClickListener {
            var titol = "Protectora d'animals"
            var AnyI = binding.etAnyin.text.toString().toInt()      // convertir-ho en int o amb parse
            var MesI = binding.etMesin.text.toString().toInt()
            var DiaI = binding.etDiain.text.toString().toInt()
            var AnyF = binding.etAnyfi.text.toString().toInt()
            var MesF = binding.etMesfi.text.toString().toInt()
            var DiaF = binding.etDiafi.text.toString().toInt()
            var inici = Calendar.getInstance()
            var final = Calendar.getInstance()
            inici.set(AnyI, MesI, DiaI,0,0)
            final.set(AnyF,MesF,DiaF,0,0)
            addEvent(titol,inici.timeInMillis, final.timeInMillis)
        }

        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment navCalendari.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            navCalendari().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun addEvent( title:String, begin:Long, end: Long){
        val intent = Intent(Intent.ACTION_INSERT).apply{
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, title)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
        }
        startActivity(intent)

    }
}