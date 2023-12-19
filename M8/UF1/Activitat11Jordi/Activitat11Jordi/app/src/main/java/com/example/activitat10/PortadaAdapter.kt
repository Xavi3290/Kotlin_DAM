package com.example.activitat10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.activitat10.databinding.CardviewPortadaBinding


class PortadaAdapter(val llista: java.util.ArrayList<portada>) :
    RecyclerView.Adapter<PortadaAdapter.ViewHolder>() {
    class ViewHolder(val card_binding: CardviewPortadaBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(card_binding.root) {
        val imatge = card_binding.ivCardviewPortada
        val descripcio = card_binding.tvCardviewPortada
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardviewPortadaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.imatge.context).load(llista[position].imatge).into(holder.imatge)
        holder.descripcio.setText(llista[position].descripcio)
        holder.card_binding.root.setOnClickListener {


        when (position) {
                1 -> {
                    navController.navigate(R.id.action_nav_ini_to_computers)
                }
                0 -> navController.navigate(R.id.action_nav_ini_to_smartphones)
                2 -> navController.navigate(R.id.action_nav_ini_to_tv)
                3 -> navController.navigate(R.id.action_nav_ini_to_mobility)
            }
        }
    }

    override fun getItemCount() = llista.size
}