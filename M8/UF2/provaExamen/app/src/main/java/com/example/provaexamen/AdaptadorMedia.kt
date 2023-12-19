package com.example.provaexamen

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.provaexamen.BD.Media
import com.example.provaexamen.databinding.CardviewfotoBinding

class AdaptadorMedia (var llista: ArrayList<Media>): RecyclerView.Adapter<AdaptadorMedia.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val binding = CardviewfotoBinding.bind(view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return ViewHolder(layout.inflate(R.layout.cardviewfoto, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.nom.setText(llista[position].descripcio)
        holder.binding.datahora.setText(llista[position].datahora)
        holder.view.setOnClickListener {

        val intent = Intent(holder.view.context, FotoGalleryActivity::class.java)
        intent.putExtra("descripcio", llista[position].descripcio)
        intent.putExtra("url", llista[position].url)
        intent.putExtra("datahora", llista[position].datahora)
        holder.view.context.startActivity(intent)


        }
    }

    override fun getItemCount() = llista.size


}