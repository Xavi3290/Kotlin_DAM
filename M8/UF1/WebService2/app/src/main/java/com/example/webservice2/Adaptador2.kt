package com.example.webservice2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webservice2.databinding.CardView1Binding
import com.example.webservice2.databinding.CardView2Binding
import com.example.webservice2.dataclasses.ImageCatsResponse

class Adaptador2(val llista: List<ImageCatsResponse>):
    RecyclerView.Adapter<Adaptador2.ViewHolder>() {
    class ViewHolder(val vista: View): RecyclerView.ViewHolder(vista) {
        private val binding = CardView2Binding.bind(vista)
        fun bind(item: ImageCatsResponse){
            println(item.url)
            Glide.with(vista.context).load(item.url).into(binding.ivCat)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adaptador2.ViewHolder {
        val layout= LayoutInflater.from(parent.context)
        return Adaptador2.ViewHolder(layout.inflate(R.layout.card_view2, parent, false))

    }

    override fun onBindViewHolder(holder: Adaptador2.ViewHolder, position: Int) {
        val item = llista[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return llista.size
    }
}