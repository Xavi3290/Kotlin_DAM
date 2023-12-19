package com.example.activitat10

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductesAdapter(var llista: ArrayList<producte>): RecyclerView.Adapter<ProductesAdapter.ViewHolder>() {
    class ViewHolder(val vista: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(vista) {
        val imatge = vista.findViewById<ImageView>(R.id.image)
        val descripcio = vista.findViewById<TextView>(R.id.descripcio)
        val preu = vista.findViewById<TextView>(R.id.preu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = LayoutInflater.from(parent.context)
        var vh : ProductesAdapter.ViewHolder? = null
        when (viewType) {
            1 -> vh = ViewHolder(layout.inflate(R.layout.cardview_productes, parent, false))
            2 -> vh = ViewHolder(layout.inflate(R.layout.cardview_productes2, parent, false))
            3 -> vh = ViewHolder(layout.inflate(R.layout.cardview_productes3, parent, false))
        }
        return vh!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.descripcio.setText(llista[position].descripcio)
        holder.preu.setText(llista[position].preu.toString()+" €")
        Glide.with(holder.vista.context).load(llista[position].imatge).into(holder.imatge)

        holder.vista.setOnClickListener {
            val intent = Intent(holder.vista.context, FitxaProducte::class.java)
            intent.putExtra("codiproducte", llista[position].codi)
            startActivity(holder.vista.context, intent, null)
        }
    }

    fun updateLlista(llistanova: ArrayList<producte>){
        llista = llistanova
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = llista[position].tipusCarview

    override fun getItemCount(): Int = llista.size
}