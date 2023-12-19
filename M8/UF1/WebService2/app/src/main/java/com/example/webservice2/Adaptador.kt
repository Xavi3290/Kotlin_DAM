package com.example.webservice2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webservice2.databinding.CardView1Binding
import com.example.webservice2.dataclasses.BreedsCatsResponse

class Adaptador(val llista: List<BreedsCatsResponse>): RecyclerView.Adapter<Adaptador.ViewHolder>() {
    class ViewHolder(val vista: View): RecyclerView.ViewHolder(vista) {
        val nom = vista.findViewById<TextView>(R.id.tv1)
        val imatge = vista.findViewById<ImageView>(R.id.ivCat)
    }

    override fun onBindViewHolder(holder: Adaptador.ViewHolder, position: Int) {
        holder.nom.setText(llista[position].name)
        Glide.with(holder.vista.context).load(llista[position].image!!.url).into(holder.imatge)
        println(llista[position].image!!.url)

        holder.vista.setOnClickListener {
            val intent = Intent(holder.vista.context, BreedDetails::class.java).apply {
                putExtra("id", llista[position].id)
            }
            holder.vista.context.startActivity(intent)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adaptador.ViewHolder {
        val layout= LayoutInflater.from(parent.context)
        return ViewHolder(layout.inflate(R.layout.card_view1, parent,false))
    }





    override fun getItemCount(): Int {
        return llista.size
    }
}