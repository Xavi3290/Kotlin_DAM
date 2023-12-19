package com.example.activitat5maps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.activitat5maps.bd.Ubicacio

class Adaptador(var llista: List<Ubicacio>) : RecyclerView.Adapter<Adaptador.ViewHolder>() {
    private var seleccionat: Int? = null

    class ViewHolder(var vista: View) : RecyclerView.ViewHolder(vista) {
        var descrip = vista.findViewById<TextView>(R.id.tvDescripcio)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layout = LayoutInflater.from(parent.context)

        if (viewType == 0)
            return ViewHolder(layout.inflate(R.layout.cardview1, parent, false))
        else
            return ViewHolder(layout.inflate(R.layout.cardview2, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.descrip.setText(llista[position].ubicacio)
        holder.vista.setOnClickListener {
            for (i in 0..llista.size - 1) {
                if (i == position)
                    if (llista[i].tipuscard == 0)
                        llista[i].tipuscard = 1
                    else {
                        llista[i].tipuscard = 0
                    }
                else
                    llista[i].tipuscard = 0
            }
            seleccionat = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = llista.size

    override fun getItemViewType(position: Int) = llista[position].tipuscard

    fun getSeleccionat() = seleccionat

}
