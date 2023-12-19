package com.example.exemplerecycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


//Al adaptador le has de meter las funciones abstract
class Adaptador(val llista:java.util.ArrayList<Element>):RecyclerView.Adapter<Adaptador.viewholder>() {

    //Agafa la representacio de cada un del cardview
    class viewholder (val view: View):ViewHolder(view){
        val imatge = view.findViewById<ImageView>(R.id.iv_cardview)
        val descripcio = view.findViewById<TextView>(R.id.tv_cardview)

    }

    //infla la cardview per cadascun dels elements
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layout = LayoutInflater.from(parent.context)
        return viewholder(layout.inflate(R.layout.cardview, parent, false))
    }

    //Enllaça cadaescun dels elements individuals (cadview) amb els elements de la llista
    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.imatge.setImageResource(llista[position].imatge)
        holder.descripcio.setText(llista[position].descripcio)
        holder.view.setOnClickListener{
            Toast.makeText(holder.view.context, "Has fet click a "+llista[position].descripcio,Toast.LENGTH_LONG).show()
        }
    }

    // indica el nombre d'elements que tindra la llista
    override fun getItemCount(): Int {
        return llista.size
    }
}