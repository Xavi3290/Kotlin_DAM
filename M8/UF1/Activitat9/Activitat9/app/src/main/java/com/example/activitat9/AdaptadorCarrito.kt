package com.example.activitat9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.activitat9.databinding.CardviewCarritoBinding

class AdaptadorCarrito (var llista: java.util.ArrayList<ProducteCarret>) :
    RecyclerView.Adapter<AdaptadorCarrito.viewholder>() {
    class viewholder (val view: View) : RecyclerView.ViewHolder(view){

        val binding = CardviewCarritoBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layout = LayoutInflater.from(parent.context)
        return viewholder(layout.inflate(R.layout.cardview_carrito, parent, false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        Glide.with(holder.binding.ivImatge.context).load(llista[position].imatge)
            .into(holder.binding.ivImatge)
        holder.binding.tvDescripcio.setText(llista[position].descripcio)

        holder.binding.bEliminar.setOnClickListener {
            listCarrito.remove(llista[position])
        }

    }

    override fun getItemCount(): Int {
        return llista.size
    }
}