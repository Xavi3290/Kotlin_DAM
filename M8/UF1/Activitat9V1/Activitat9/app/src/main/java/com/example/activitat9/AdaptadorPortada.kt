package com.example.activitat9

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.activitat9.databinding.CardviewPortadaBinding

class AdaptadorPortada(val llista: java.util.ArrayList<Portada>) :
    RecyclerView.Adapter<AdaptadorPortada.viewholder>() {
    class viewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardviewPortadaBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layout = LayoutInflater.from(parent.context)
        return viewholder(layout.inflate(R.layout.cardview_portada, parent, false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        Glide.with(holder.binding.ivImatge.context).load(llista[position].imatge)
            .into(holder.binding.ivImatge)
        holder.binding.tvDescripcio.setText(llista[position].descripcio)

        holder.view.setOnClickListener {
            Toast.makeText(
                holder.view.context,
                "Has fet click a " + llista[position].descripcio,
                Toast.LENGTH_LONG
            ).show()

            val intent = Intent(holder.view.context, ProducteActivity::class.java)
            intent.putExtra("tipusproducte", llista[position].tipusProducte)
            holder.view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return llista.size
    }

}