package com.example.activitat8

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adaptador(var llista: ArrayList<Dada>) : RecyclerView.Adapter<Adaptador.ViewHolder>() {
    class ViewHolder(val vista: View) : RecyclerView.ViewHolder(vista) {
        val imatge = vista.findViewById<ImageView>(R.id.iv_cardview)
        val tempe = vista.findViewById<TextView>(R.id.temp_cardview)
        val humitat = vista.findViewById<TextView>(R.id.hum_cardview)
        val vent = vista.findViewById<TextView>(R.id.vent_cardview)
        val precipitacio = vista.findViewById<TextView>(R.id.pre_cardview)
    }

    // per que cambi de cardview amb la variable tipus, que te dos layout diferents
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        if (viewType == 1)
            return ViewHolder(layout.inflate(R.layout.cardview, parent, false))
        else
            return ViewHolder(layout.inflate(R.layout.cardview2, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.humitat.setText("Humitat: " + dades[position].humitat + "%")
        holder.tempe.setText(dades[position].graus.toString() + "ºC")
        holder.vent.setText("Velocitat vent: " + dades[position].vent + "Km/h")
        holder.precipitacio.setText("Precipitació: " + dades[position].precipitacio + "%")


        Glide.with(holder.vista.context).load(arrel_url + "64" + dades[position].url)
            .into(holder.imatge)

        holder.vista.setOnClickListener {
            val intent = Intent(holder.vista.context, Fitxa::class.java)
            intent.putExtra("hora", position)
            startActivity(holder.vista.context, intent, null)
        }

    }

    override fun getItemCount() = llista.size

    override fun getItemViewType(position: Int) = llista[position].tipus   //Per poder cambiar de layout de cardview

    fun updateDades(novallista: ArrayList<Dada>){       //Perque en el moment de filtrar pugui carregar la nova llista
        llista = novallista
        notifyDataSetChanged()
    }
}