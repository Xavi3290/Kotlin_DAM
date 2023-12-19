package com.example.retrofit1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador(val llista: List<Data>): RecyclerView.Adapter<Adaptador.ViewHolder>() {
    class ViewHolder(val vista: View): RecyclerView.ViewHolder(vista) {
        val nomempleat = vista.findViewById<TextView>(R.id.nomempleat)
        val edatempleat = vista.findViewById<TextView>(R.id.edatempleat)
        val souempleat = vista.findViewById<TextView>(R.id.souempleat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adaptador.ViewHolder {
        val layout= LayoutInflater.from(parent.context)
        return ViewHolder(layout.inflate(R.layout.card_view1, parent,false))
    }

    override fun onBindViewHolder(holder: Adaptador.ViewHolder, position: Int) {
        holder.nomempleat.setText(llista[position].employee_name)
        holder.souempleat.setText("Sou: "+llista[position].employee_salary)
        holder.edatempleat.setText("Edat: "+llista[position].employee_age)
    }



    override fun getItemCount(): Int {
        return llista.size
    }
}