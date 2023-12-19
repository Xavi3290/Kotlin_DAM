package com.marjasoft.ejcamerax

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marjasoft.ejcamerax.bd.Media

class Adapter(var llista: ArrayList<Media>): RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        var nom = view.findViewById<TextView>(R.id.nom)
        var data = view.findViewById<TextView>(R.id.datahora)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        if (viewType == 1)
            return ViewHolder(layout.inflate(R.layout.cardview, parent, false))
        else
            return ViewHolder(layout.inflate(R.layout.cardview2, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nom.setText(llista[position].descripcio)
        holder.data.setText(llista[position].datahora)
        holder.view.setOnClickListener {
            if (llista[position].tipus=="Foto") {
                val intent = Intent(holder.view.context, FotoGallery::class.java)
                intent.putExtra("descripcio", llista[position].descripcio)
                intent.putExtra("url", llista[position].url)
                intent.putExtra("datahora", llista[position].datahora)
                holder.view.context.startActivity(intent)
            }else {
                val intent = Intent(holder.view.context, VideoGallery::class.java)
                intent.putExtra("descripcio", llista[position].descripcio)
                intent.putExtra("url", llista[position].url)
                intent.putExtra("datahora", llista[position].datahora)
                holder.view.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = llista.size

    override fun getItemViewType(position: Int): Int {
        if (llista[position].tipus=="Foto")
            return 1
        else
            return 2
    }
}