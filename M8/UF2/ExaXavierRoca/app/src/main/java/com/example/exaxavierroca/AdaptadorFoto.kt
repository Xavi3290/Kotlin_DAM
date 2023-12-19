package com.example.exaxavierroca

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exaxavierroca.databinding.CardviewfotoBinding

class AdaptadorFoto(var llista: ArrayList<Media>): RecyclerView.Adapter<AdaptadorFoto.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val binding = CardviewfotoBinding.bind(view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return ViewHolder(layout.inflate(R.layout.cardviewfoto, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.binding.ivGallery.context).load(llista[position].url)
            .into(holder.binding.ivGallery)



        holder.view.setOnClickListener {

            val intent = Intent(holder.view.context, MostrarTotActivity::class.java)
            intent.putExtra("position", position)
            holder.view.context.startActivity(intent)

        }


    }

    override fun getItemCount() = llista.size
}