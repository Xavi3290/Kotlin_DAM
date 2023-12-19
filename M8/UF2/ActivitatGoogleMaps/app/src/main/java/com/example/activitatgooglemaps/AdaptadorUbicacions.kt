package com.example.activitatgooglemaps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.activitatgooglemaps.BD.Ubicacio
import com.example.activitatgooglemaps.BD.listseleccio
import com.example.activitatgooglemaps.databinding.CardviewBinding

class AdaptadorUbicacions (var llista: java.util.ArrayList<Ubicacio>) :
    RecyclerView.Adapter<AdaptadorUbicacions.viewholder>()  {
    class viewholder (val view: View) : RecyclerView.ViewHolder(view){

        val binding = CardviewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layout = LayoutInflater.from(parent.context)
        return viewholder(layout.inflate(R.layout.cardview, parent, false))
    }

    override fun getItemCount(): Int {
        return llista.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.binding.tvDescripcio.setText(llista[position].descripcio)
        holder.binding.tvLatitud.setText(llista[position].latitud.toString())
        holder.binding.tvLongitud.setText(llista[position].longitud.toString())

        holder.binding.bElegir.setOnClickListener {

            var cont = 0

            if(cont >= 2){
                listseleccio.add(llista[position])
                cont++
                Toast.makeText(holder.binding.bElegir.context, "Has elegit aquesta ubicació", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(holder.binding.bElegir.context, "No es pot elegir més ubicacions", Toast.LENGTH_LONG).show()
            }

        }

    }
}